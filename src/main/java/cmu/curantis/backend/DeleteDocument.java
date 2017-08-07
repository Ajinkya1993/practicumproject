package cmu.curantis.backend;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.DocumentMgmtDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.CaregiverCircleBean.Identity;
import cmu.curantis.entities.DocumentMgmtBean;
import cmu.curantis.inputbeans.DocumentInput;
import cmu.curantis.outputbeans.DocumentOutput;

@Path("/deleteDocument")
public class DeleteDocument {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DocumentOutput deleteDocuments(DocumentInput input) {
		
		DocumentOutput output = new DocumentOutput();
		
		if (input.getEmail() == null || input.getEmail().length() == 0 || input.getCircleId() == null || input.getCircleId() == 0 || input.getService() <= 0
				|| input.getDocumentUrl() == null || input.getDocumentUrl().length() == 0 || input.getDocumentName() == null || input.getDocumentName().length() == 0) {
			output.setSuccess(false);
			output.setMessage("Missing parameters!");
	    	return output;
		}
		
		
		//check if its a primary caregiver
		//this is when it is provided from frontend else will be checked in DAO
		/*if(input.getAccessLevel() == false) {
			output.setSuccess(false);
			output.setMessage("You do not have the access level to delete the document! Please contact your circle admin!");
	    	return output;
		}*/	
		
		
		CaregiverCircleDAO cgcirc = new CaregiverCircleDAO();
		//DAO
		DocumentMgmtDAO docdao = new DocumentMgmtDAO();
			
		//If delete caergiver from circle is added later, dividing into two transactions would be better
			/* Begin transaction */
		Session session_init = SessionUtil.getSession();        
        Transaction tx_init = session_init.beginTransaction();
        
        	//method to get the list of users of a circle
		List<CaregiverCircleBean> lst = cgcirc.getByCircleId(session_init, input.getCircleId());
		tx_init.commit();
		session_init.close();
			/* End transaction */
		
			/* Begin Transaction */
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
			//iterate over users of the circle
		for(CaregiverCircleBean cub: lst) {
			//if not primary caregiver then goto next
			if(cub.getPrimaryCaregiver() == false) {
				continue;
			}
			//Generate main key
			long circleid = cub.getIdentity().getCircleID();
			String email = cub.getIdentity().getEmail();
			
			StringBuilder sb = new StringBuilder();
			sb.append(circleid);
			sb.append(">");
			sb.append(email);
			sb.append(">");
			sb.append(input.getService());
			String mainkey = sb.toString();
			
			//Docmgmt Bean
			DocumentMgmtBean docmgmt = new DocumentMgmtBean();
			docmgmt.setIdentity();
			docmgmt.getIdentity().setDocumentName(input.getDocumentName());
			docmgmt.getIdentity().setMainkey(mainkey);
			docmgmt.setAccessLevel(true);
			docmgmt.setDocumentUrl(input.getDocumentUrl());
			//debug statements for help
		/*	try {
				System.out.println("In Document check access level : success DAO "+docdao.checkAccessLevel(session, docmgmt).get("success"));
				System.out.println("In Document check access level: access level DAO "+docdao.checkAccessLevel(session, docmgmt).get("accessLevel"));
			} catch (JSONException e) {
				e.printStackTrace();
			}*/
			try {
				if(docdao.checkAccessLevel(session, docmgmt).getBoolean("success") != true) {
					output.setSuccess(false);
					output.setMessage("The document does not exist");
			    	return output;
				}
				if(docdao.checkAccessLevel(session, docmgmt).getBoolean("accessLevel") != true) {
					output.setSuccess(false);
					output.setMessage("You do not have the access level to delete the document! Please contact your circle admin!");
			    	return output;	
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Boolean result = docdao.deleteDocument(session, docmgmt);
			if (result == false) {
				output.setMessage("Document could not be deleted!");
				output.setSuccess(false);
				return output;
			}
		} 
        tx.commit();
			/* End transaction */
        
		output.setSuccess(true);
		output.setMessage("Deleted document!");
        session.close();
        return output;
	}
}
