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
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		String targetEmail = input.getTargetEmail();
		String updateMainkey = input.getCircleId() + ">" + targetEmail + ">" + input.getService();
		/**
		 * Success cases: 1. already exist, just update access level 
		 * 2. user not exist, adding a new row into the database
		 */
		List<Docnest> list = docmgmt.getByMainkey(session, updateMainkey);
		boolean flag = false;
		Docnest docnest = null;
		if (list == null || list.size() == 0) {
			output.setMessage("No Document Exist!");
			output.setSuccess(false);
		} else {

			for (Docnest doc : list) {
				if (doc.getDocname().equals(input.getDocumentName())) {
					flag = true;
					docnest = doc;
				}
			}
			if (flag) {
				DocumentMgmtBean updateBean = new DocumentMgmtBean();
				updateBean.setAccessLevel(input.getAccessLevel());
				updateBean.setDocumentUrl(docnest.docurl);
				updateBean.setIdentity();
				updateBean.getIdentity().setDocumentName(docnest.docname);
				updateBean.getIdentity().setMainkey(updateMainkey);
				docmgmt.updateDocument(session, updateBean);
			} else {
				List<Docnest> docList = docmgmt.getByMainkey(session, input.getMainkey());
				String url = null;
				for (Docnest d : docList) {
					if (d.getDocname().equals(input.getDocumentName())) {
						url = d.getDocurl();
					}
				}
				DocumentMgmtBean updateBean = new DocumentMgmtBean();
				updateBean.setDocumentUrl(url);
				updateBean.setAccessLevel(input.getAccessLevel());
				updateBean.setIdentity();
				updateBean.getIdentity().setDocumentName(input.getDocumentName());
				updateBean.getIdentity().setMainkey(updateMainkey);
				docmgmt.create(session, updateBean);
			}
		}
		output.setMessage("Shared Document Success!");
		output.setSuccess(true);
		tx.commit();
		session.close();
		return output;
	}
}
