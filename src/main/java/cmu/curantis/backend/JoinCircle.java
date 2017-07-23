package cmu.curantis.backend;

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
import cmu.curantis.outputbeans.CircleOutput;

@Path("/joincircle")
public class JoinCircle {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public CircleOutput joinCircle(CircleInput input) {
	    CircleOutput output = new CircleOutput();
	    if (input.getEmail() == null || input.getCircleId() == 0) {
	        output.setMessage("Missing email or circleId!");
	        output.setSuccess(false);
	        return output;
	    }
	    CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
	    Session session = SessionUtil.getSession();
	    Transaction tx = session.beginTransaction();
	   
	    CaregiverCircleBean circle = caregiverCircleDAO.getByEmailAndId(session, input.getEmail(), input.getCircleId());
	    if (circle == null) {
	        output.setMessage("Not invited to this circle!");
	        output.setSuccess(false);
	    } else {
	        if (circle.getJoinStatus()) {
	            output.setMessage("Joined already!");
	            output.setSuccess(false);
	        } else {
	            circle.setGeorelationship(input.getGeoRel());
	            circle.setRelationshipNature(input.getNatureOfRel());
	            circle.setJoinStatus(true);
	            caregiverCircleDAO.update(session, circle);
	            output.setCircleId(input.getCircleId());
	            output.setSuccess(true);
	            output.setMessage("Circle joined!");
	        }
	    }
	    tx.commit();
	    session.close();
		return output;
	}
}
