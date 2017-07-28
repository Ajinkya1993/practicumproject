package cmu.curantis.backend;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.DocumentMgmtDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.DocumentMgmtBean;
import cmu.curantis.inputbeans.DocumentInput;
import cmu.curantis.outputbeans.DocumentOutput;

@Path("/addDocument")
public class AddDocument {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DocumentOutput addDocuments(DocumentInput input) {
		
		DocumentOutput output = new DocumentOutput();
		
		if (input.getEmail() == null || input.getEmail().length() == 0 || input.getCircleId() == null || input.getCircleId() <= 0 || input.getService() <= 0
				|| input.getDocumentUrl() == null || input.getDocumentUrl().length() == 0 || input.getDocumentName() == null || input.getDocumentName().length() == 0) {
			output.setSuccess(false);
			output.setMessage("Missing parameters!");
	    	return output;
		}
		
		//to check if document name already exists
		Session session_init = SessionUtil.getSession();        
        Transaction tx_init = session_init.beginTransaction();
        //method to get the list of users of a circle
        CaregiverCircleDAO cgcircl = new CaregiverCircleDAO();
		List<CaregiverCircleBean> lst = cgcircl.getByCircleId(session_init, input.getCircleId());
		tx_init.commit();
		session_init.close();
		DocumentMgmtDAO docdao_init = new DocumentMgmtDAO();
		
		Session session_mi = SessionUtil.getSession();        
        Transaction tx_mi = session_mi.beginTransaction();
			//iterate over users of the circle
        for(CaregiverCircleBean cub: lst) {
        	
        	cub.setIdentity();
			long circleid = cub.getIdentity().getCircleID();
			String email = cub.getIdentity().getEmail();
        	
        	StringBuilder sb = new StringBuilder();
			sb.append(circleid);
			sb.append(">");
			sb.append(email);
			sb.append(">");
			sb.append(input.getService());
			String mainkey = sb.toString();
			
			DocumentMgmtBean docmgmt = new DocumentMgmtBean();
			docmgmt.setIdentity();
			docmgmt.getIdentity().setDocumentName(input.getDocumentName());
			docmgmt.getIdentity().setMainkey(mainkey);
			docmgmt.setAccessLevel(true);
			docmgmt.setDocumentUrl(input.getDocumentUrl());
			Boolean result = docdao_init.checkDocument(session_mi, docmgmt);
			if (result == false) {
				output.setMessage("Document with same name exists, please change the name!");
				output.setSuccess(false);
				return output;
			}
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
        Boolean result = docdao.create(session, docmgmt); 
		tx.commit();
		if (result == false) {
			output.setMessage("Document could not be added!");
			output.setSuccess(false);
			return output;
		}
		output.setSuccess(true);
		output.setMessage("Added document!");
        session.close();
        return output;
	}
}
