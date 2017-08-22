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
import cmu.curantis.outputbeans.ListDocumentsOutput;

/**
 * The resource that deals with retrieving documents for a careteam's service.
 * 
 * Input fields: email, password
 * Input example:
 * {"email":"john@gmail.com, "service":"2", "circleId" "1"}
 * 
 * Output fields: accessLevel, docname, docurl, message, success
 * Output example:
 * {
    "listofdocs": [
        {
            "accessLevel": true,
            "docAccessLevel": true,
            "docname": "abc.pdf",
            "docurl": "https://team-curantis-jing.s3-us-west-2.amazonaws.com/Team1/SeniorHousing/abc.pdf"
        }
    ],
    "message": "Received list of documents!",
    "success": true
}
 * 
 * @author namitasibal
 *
 */
@Path("/listDocuments")
public class ListDocuments {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ListDocumentsOutput listDocuments(DocumentInput input) {
		ListDocumentsOutput output = new ListDocumentsOutput();
		
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
		List<DocumentMgmtDAO.Docattr> result = dao.getListOfDocs(session, mainKey);
		
		
		tx.commit();
		if (result == null) {
			output.setMessage("No documents available!");
			output.setSuccess(false);
			output.setListofdocs(null);
			return output;
		}
		output.setMessage("Received list of documents!");
		output.setSuccess(true);
		output.setListofdocs(result);
		//output.setAccessLevel(true);
        session.close();
        return output;
	}
}
