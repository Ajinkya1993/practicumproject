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
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.inputbeans.DocumentInput;
import cmu.curantis.outputbeans.DocumentOutput;

@Path("/listDocuments")
public class ListDocuments {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DocumentOutput listDocuments(DocumentInput input) {
		DocumentOutput output = new DocumentOutput();
		
		if (input.getEmail() == null || input.getEmail().length() == 0 || input.getCircleId() == null || input.getCircleId() == 0 || input.getService() == 0) {
			output.setMessage("Missing parameters!");
	    	output.setSuccess(false);
	    	output.setListofdocs(null);
	    	return output;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(input.getCircleId());
		sb.append(">");
		sb.append(input.getEmail());
		sb.append(">");
		sb.append(input.getService());
		String mainKey = sb.toString();
		DocumentMgmtDAO dao = new DocumentMgmtDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		List<DocumentMgmtDAO.Docnest> result = dao.getByMainkey(session, mainKey);
		tx.commit();
		if (result == null) {
			output.setMessage("No documents exist!");
			output.setSuccess(true);
			output.setListofdocs(null);
			return output;
		}
		output.setMessage("Received list of documents!");
		output.setSuccess(true);
		output.setListofdocs(result);
		output.setAccessLevel(true);
        session.close();
        return output;
	}
}
