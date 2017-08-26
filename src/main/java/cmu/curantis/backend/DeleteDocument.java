/**
 * This resource allows the caregiver with the right access level to delete a document.
 * 
 * Input fields: email, circleId, service, documentName
 * Input example:
 * {
 * "email":"john@gmail.com",
 * "circleId":1,
 * "service":1,
 * "documentName":"document"
 * }
 * 
 * Output fields: message, success
 * Output Example:
 * {
 * "message": "Deleted document!",
 *   "success": true
 * }
 * 
 * @author curantisTeamCMU
 *
 */

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
import org.json.JSONObject;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.DocumentMgmtDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
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
				|| input.getDocumentName() == null || input.getDocumentName().length() == 0) {
			output.setSuccess(false);
			output.setMessage("Missing parameters!");
	    	return output;
		}
		
		CaregiverCircleDAO cgcirc = new CaregiverCircleDAO();
		//DAO
		DocumentMgmtDAO docdao = new DocumentMgmtDAO();
			
		//If delete caergiver from circle is added later, dividing into two transactions would be better
			/* Begin transaction */
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        
        DocumentMgmtBean curUser = new DocumentMgmtBean();
		curUser.setIdentity();
		curUser.getIdentity().setDocumentName(input.getDocumentName());
		String key = input.getCircleId() + ">" + input.getEmail() + ">" + input.getService();
		curUser.getIdentity().setMainkey(key);
		try {
			JSONObject res = docdao.checkAccessLevel(session, curUser);
			if (!res.getBoolean("success") || !res.getBoolean("accessLevel")) {
				output.setSuccess(false);
				output.setMessage("Sorry, you don't have the permission to view access levels");
				tx.commit();
		        session.close();
		        return output;
			}
		} catch (JSONException e) {
			output.setSuccess(false);
			output.setMessage("No such document!");
			tx.commit();
	        session.close();
	        return output;
		}
        
        	//method to get the list of users of a circle
		List<CaregiverCircleBean> lst = cgcirc.getByCircleId(session, input.getCircleId());
			//iterate over users of the circle
		for(CaregiverCircleBean cub: lst) {

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
			//docmgmt.setAccessLevel(true);
			//docmgmt.setDocumentUrl(input.getDocumentUrl());
			//debug statements for help
		/*	try {
				System.out.println("In Document check access level : success DAO "+docdao.checkAccessLevel(session, docmgmt).get("success"));
				System.out.println("In Document check access level: access level DAO "+docdao.checkAccessLevel(session, docmgmt).get("accessLevel"));
			} catch (JSONException e) {
				e.printStackTrace();
			}*/
			docdao.deleteDocument(session, docmgmt);
		} 
        tx.commit();
        session.close();
			/* End transaction */
        
		output.setSuccess(true);
		output.setMessage("Deleted document!");
        return output;
	}
}
