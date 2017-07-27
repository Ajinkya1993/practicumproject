package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import cmu.curantis.dao.DocumentMgmtDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.DocumentMgmtBean;
import cmu.curantis.inputbeans.DocumentInput;
import cmu.curantis.outputbeans.DocumentOutput;

@Path("/deleteDocuments")
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
		if(input.getAccessLevel() == false) {
			output.setSuccess(false);
			output.setMessage("You do not have the access level to delete the document! Please contact your circle admin");
	    	return output;
		}
		
		//Generate main key
		StringBuilder sb = new StringBuilder();
		sb.append(input.getCircleId());
		sb.append(">");
		sb.append(input.getEmail());
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
		
		//DAO
		DocumentMgmtDAO docdao = new DocumentMgmtDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		//include method to get the users of a circle

        //iterate over users of the circle
        
        Boolean result = docdao.deleteDocument(session, docmgmt); 
		
        
        tx.commit();
		
		
		if (result == false) {
			output.setMessage("Document could not be deleted!");
			output.setSuccess(false);
			return output;
		}
		output.setSuccess(true);
		output.setMessage("Deleted document!");
        session.close();
        return output;
	}
}
