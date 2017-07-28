package cmu.curantis.backend;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cmu.curantis.inputbeans.DocumentInput;
import cmu.curantis.inputbeans.RegisterInput;
import cmu.curantis.outputbeans.DocumentOutput;
import cmu.curantis.outputbeans.LoginOutput;
import cmu.curantis.dao.DocumentMgmtDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.DocumentMgmtBean;

@Path("/testdoc")
public class testDocMgmt {
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public DocumentOutput addDocument (DocumentInput input) {
		DocumentOutput output = new DocumentOutput();
		
		DocumentMgmtBean bean = new DocumentMgmtBean();
		bean.setIdentity();
		bean.getIdentity().setDocumentName("mydoc");
		bean.getIdentity().setMainkey("10>ajinkya>1");
		bean.setDocumentUrl("www.s3doc.com");
		bean.setAccessLevel(true);
		DocumentMgmtDAO dao = new DocumentMgmtDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		boolean result = dao.create(session, bean);
		tx.commit();
		if (result == false) {
			output.setMessage("unsuccessful");
			output.setSuccess(false);
			return output;
		}
		output.setMessage("successfully added doc");
		output.setSuccess(true);
        session.close();
        return output;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/share")
	public DocumentOutput shareDocument (DocumentInput input) {
		DocumentOutput output = new DocumentOutput();
		
		DocumentMgmtBean bean = new DocumentMgmtBean();
		bean.setIdentity();
		bean.getIdentity().setDocumentName("mydoc");
		bean.getIdentity().setMainkey("10>ajinkya>1");
		bean.setDocumentUrl(input.getDocumentUrl());
		bean.setAccessLevel(input.getAccessLevel());
		DocumentMgmtDAO dao = new DocumentMgmtDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		boolean result = dao.updateDocument(session, bean);
		tx.commit();
		if (result == false) {
			output.setMessage("unsuccessful");
			output.setSuccess(false);
			return output;
		}
		output.setMessage("successfully updated doc");
		output.setSuccess(true);
        session.close();
        return output;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public DocumentOutput deleteDocument (DocumentInput input) {
		DocumentOutput output = new DocumentOutput();
		
		DocumentMgmtBean bean = new DocumentMgmtBean();
		bean.setIdentity();
		bean.getIdentity().setDocumentName("mydoc");
		bean.getIdentity().setMainkey("10>ajinkya>1");
		DocumentMgmtDAO dao = new DocumentMgmtDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		boolean result = dao.deleteDocument(session, bean);
		tx.commit();
		if (result == false) {
			output.setMessage("unsuccessful");
			output.setSuccess(false);
			return output;
		}
		output.setMessage("Deleted document");
		output.setSuccess(true);
        session.close();
        return output;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/getlist")
	public DocumentOutput getlistofDocument (DocumentInput input) throws JSONException {
		DocumentOutput output = new DocumentOutput();
		
		DocumentMgmtBean bean = new DocumentMgmtBean();
		String mainkey =  new String("10>ajinkya>1");
		DocumentMgmtDAO dao = new DocumentMgmtDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		List<DocumentMgmtDAO.Docnest> result = dao.getByMainkey(session, mainkey);
		tx.commit();
		if (result == null) {
			output.setMessage("No documents exist");
			output.setSuccess(true);
			output.setListofdocs(null);
			return output;
		}
		/*
		JSONArray docslist = new JSONArray();
		for(DocumentMgmtDAO.Docnest docnest : result) {
			JSONObject obj = new JSONObject();
			System.out.println("docname: " +  docnest.getDocname());
			obj.append("docname", docnest.getDocname());
			obj.append("docurl", docnest.getDocurl());
			docslist.put(obj);
		}
		System.out.println("here here: " + docslist.getJSONObject(0).getString("docname"));
		*/
		output.setMessage("Got list of documents");
		output.setSuccess(true);
		output.setListofdocs(result);
        session.close();
        return output;
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/checkAccess")
	public DocumentOutput checkAccesss (DocumentInput input) throws JSONException {
		DocumentOutput output = new DocumentOutput();
		
		DocumentMgmtBean bean = new DocumentMgmtBean();
		bean.setIdentity();
		bean.getIdentity().setDocumentName("mydoc");
		bean.getIdentity().setMainkey("10>ajinkya>1");
		DocumentMgmtDAO dao = new DocumentMgmtDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		JSONObject result = dao.checkAccessLevel(session, bean);
		tx.commit();
		if (result.getBoolean("success") == false) {
			output.setMessage("Error. Access Level not found");
			output.setSuccess(false);
			return output;
		}
		output.setMessage("Success. Access Level found");
		output.setSuccess(true);
		output.setAccessLevel(result.getBoolean("accessLevel"));
        session.close();
        return output;
	}
}
