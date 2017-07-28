package cmu.curantis.backend;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import cmu.curantis.dao.DocumentMgmtDAO;
import cmu.curantis.dao.DocumentMgmtDAO.Docnest;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.DocumentMgmtBean;
import cmu.curantis.inputbeans.ShareDocumentInput;
import cmu.curantis.outputbeans.ShareDocumentOutput;

@Path("/sharedocument")
public class ShareDocument {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ShareDocumentOutput shareDocument(ShareDocumentInput input) {
		ShareDocumentOutput output = new ShareDocumentOutput();
		DocumentMgmtDAO docmgmt = new DocumentMgmtDAO();
		
		if (input.getEmail() ==  null || input.getEmail().length() == 0 ||
			input.getTargetEmail() == null || input.getTargetEmail().length() == 0 ||
			input.getCircleId() == null || input.getCircleId() <= 0 ||
			input.getService() <= 0 ||
			input.getDocumentName() == null || input.getDocumentName().length() == 0 ||
			input.getDocumentUrl() == null || input.getDocumentUrl().length() == 0) {
				output.setMessage("Missing input parameters");
				output.setSuccess(false);
				return output;
			}
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String targetEmail = input.getTargetEmail();
		String email = input.getEmail();
		String docname = input.getDocumentName();
		String docurl = input.getDocumentUrl();
		String targetMainkey = input.getCircleId() + ">" + targetEmail + ">" + input.getService();
		String mainkey = input.getCircleId() + ">" + email + ">" + input.getService();
		
		if (input.getAccessLevel() == false) {
			output.setMessage("You do not have the permission to share this document");
			output.setSuccess(false);
			tx.commit();
			session.close();
			return output;
		}
		
		List<Docnest> list = docmgmt.getByMainkey(session, targetMainkey);
		if (list == null || list.size() == 0) {
			// The targetmainkey does not have this document yet. Create new row
			
			DocumentMgmtBean newBean = new DocumentMgmtBean();
			newBean.setAccessLevel(input.getAccessLevel());
			newBean.setDocumentUrl(docurl);
			newBean.setIdentity();
			newBean.getIdentity().setDocumentName(docname);
			newBean.getIdentity().setMainkey(targetMainkey);
			docmgmt.create(session, newBean);
			output.setMessage("Shared Document Successfully!");
			output.setSuccess(true);
			tx.commit();
			session.close();
			return output;
		}
		//Need to check whether the doc being shared is the same doc the target email already has
		boolean isDocSame = false;
		for (Docnest docnest: list) {
			if (docnest.docname ==docname) {
				isDocSame = true;
				break;
			}
		}
		if (isDocSame) {
			// the target email has this document already. Updated the row.
			DocumentMgmtBean updateBean = new DocumentMgmtBean();
			updateBean.setAccessLevel(input.getAccessLevel());
			updateBean.setDocumentUrl(docurl);
			updateBean.setIdentity();
			updateBean.getIdentity().setDocumentName(docname);
			updateBean.getIdentity().setMainkey(targetMainkey);
			docmgmt.updateDocument(session, updateBean);
			output.setMessage("Shared Document Successfully!");
			output.setSuccess(true);
			tx.commit();
			session.close();
			return output;
		} else {
			// The targetmainkey exists in the table but does not have this document yet. Create new row
			DocumentMgmtBean newBean = new DocumentMgmtBean();
			newBean.setAccessLevel(input.getAccessLevel());
			newBean.setDocumentUrl(docurl);
			newBean.setIdentity();
			newBean.getIdentity().setDocumentName(docname);
			newBean.getIdentity().setMainkey(targetMainkey);
			docmgmt.create(session, newBean);
			output.setMessage("Shared Document Successfully!");
			output.setSuccess(true);
			tx.commit();
			session.close();
			return output;
		}
	}
}
