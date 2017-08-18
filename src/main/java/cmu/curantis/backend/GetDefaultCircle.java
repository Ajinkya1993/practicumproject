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
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.DefaultCircleOutput;
/**
 * The resource that returns the default careteam of a caregiver.
 * 
 * Input fields: email
 * Input example:
 * {"email":"john@gmail.com"}
 * 
 * Output fields: circleId, circleName, message, success
 * Output example:
 * {
    "circleId": 1,
    "circleName": "John Banta",
    "message": "Get default circle success!",
    "success": true
   }
 * @author jingzhu
 *
 */
@Path("/getDefaultCircle")
public class GetDefaultCircle {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DefaultCircleOutput getDefaultCircle(CircleInput input) {
		DefaultCircleOutput output = new DefaultCircleOutput();
		if (input == null || input.getEmail() == null || input.getEmail().length() == 0) {
            output.setMessage("Missing Email!");
            output.setSuccess(false);
            return output;
        }
		
		CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        
        List<CaregiverCircleBean> list = caregiverCircleDAO.getByEmail(session, input.getEmail());
        if (list != null) {
        	for (CaregiverCircleBean circle : list) {
        		if (circle.getJoinStatus()) {
                	output.setSuccess(true);
                	output.setMessage("Get default circle success!");
                	output.setCircleId(circle.getIdentity().getCircleID());
                	output.setCircleName(circle.getCirclename());
                	tx.commit();
                    session.close();
                    return output;
        		}
        	}
        }
        output.setSuccess(false);
    	output.setMessage("Not in a cicle yet.");
        
        tx.commit();
        session.close();
        return output;
	}
}
