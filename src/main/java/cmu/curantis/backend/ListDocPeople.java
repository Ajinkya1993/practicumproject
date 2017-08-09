package cmu.curantis.backend;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.DocumentMgmtDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.DocumentMgmtBean;
import cmu.curantis.inputbeans.DocumentInput;
import cmu.curantis.outputbeans.DocumentPeople;
import cmu.curantis.outputbeans.DocumentPeopleOutput;

@Path("/listpeople")
public class ListDocPeople {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public DocumentPeopleOutput listPeople(DocumentInput input) {
		DocumentPeopleOutput output = new DocumentPeopleOutput();
		
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        
        CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
        DocumentMgmtDAO docDAO = new DocumentMgmtDAO();
        //Check if the user have permission to view access levels.
        DocumentMgmtBean curUser = new DocumentMgmtBean();
		curUser.setIdentity();
		curUser.getIdentity().setDocumentName(input.getDocumentName());
		String key = input.getCircleId() + ">" + input.getEmail() + ">" + input.getService();
		curUser.getIdentity().setMainkey(key);
		try {
			JSONObject res = docDAO.checkAccessLevel(session, curUser);
			if (!res.getBoolean("success") || !res.getBoolean("accessLevel")) {
				output.setSuccess(false);
				output.setMessage("Sorry, you don't have the permission to view access levels");
				tx.commit();
		        session.close();
		        return output;
			}
		} catch (JSONException e) {
			output.setSuccess(false);
			output.setMessage("No such Document!");
			tx.commit();
	        session.close();
	        return output;
		}
        //Get the caregivers in the circle.
		List<CaregiverCircleBean> cgInCircle = caregiverCircleDAO.getByCircleId(session, input.getCircleId());
		//Check each caregiver's access level regarding the document.
		List<DocumentPeople> list = new ArrayList<DocumentPeople>();
		for (CaregiverCircleBean caregiver : cgInCircle) {
			DocumentMgmtBean bean = new DocumentMgmtBean();
			bean.setIdentity();
			bean.getIdentity().setDocumentName(input.getDocumentName());
			String mainKey = input.getCircleId() + ">" + caregiver.getIdentity().getEmail() + ">" + input.getService();
			bean.getIdentity().setMainkey(mainKey);
			try {
				JSONObject result = docDAO.checkAccessLevel(session, bean);
				if (result.getBoolean("success")) {
					DocumentPeople person = new DocumentPeople();
					person.setAccessLevel(result.getBoolean("accessLevel"));
					person.setEmail(caregiver.getIdentity().getEmail());
					list.add(person);
				}
			} catch (JSONException e) {
				System.out.println("document not found for: " + mainKey);
//				output.setSuccess(false);
//				output.setMessage(e.getMessage());
//				tx.commit();
//		        session.close();
//		        return output;
			}
		}
		
		tx.commit();
        session.close();
        
        output.setSuccess(true);
        output.setMessage("Get people success!");
        output.setPeople(list);
        return output;
	}
}
