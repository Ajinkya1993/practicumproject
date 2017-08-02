package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.SessionUtil;
import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleOutput;

@Path("/declineinvitation")
public class DeclineInvitation {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public CircleOutput declineInvitation(CircleInput input) {
	    CircleOutput output = new CircleOutput();
	    if (input.getEmail() == null || input.getEmail().length() == 0) {
	        output.setMessage("Missing email!");
	        output.setSuccess(false);
	        return output;
	    }
	    if (input.getCircleName() == null || input.getCircleName().length() == 0) {
	        output.setMessage("Missing CircleName!");
	        output.setSuccess(false);
	        return output;
	    }
	    
	    CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
	    Session session = SessionUtil.getSession();
	    Transaction tx = session.beginTransaction();
	    
	    System.out.println("Input email here is "+input.getEmail());
	    System.out.println("Input careteam here is "+input.getCircleName());
	    CaregiverCircleBean circle = caregiverCircleDAO.getByEmailAndName(session, input.getEmail(), input.getCircleName());
	    if (circle == null) {
	        output.setMessage("Not invited to this circle!"); //or this couldbe that it has been declined already
	        output.setSuccess(false);
	    } else {
	        if (circle.getJoinStatus()) {
	            output.setMessage("Joined already!");
	            output.setSuccess(false);
	        } else {
	            circle.setGeorelationship(input.getGeoRel());
	            circle.setRelationshipNature(input.getNatureOfRel());
	            circle.setJoinStatus(true);
	            boolean status = caregiverCircleDAO.delete(session, circle);
	            if (status) {
	                output.setCircleId(circle.getIdentity().getCircleID());
	                output.setSuccess(true);
	                output.setMessage("Invitation Declined!");
	            } else {
	                output.setMessage("Declining Invitation failed!");
	                output.setSuccess(false);
	            }
	        }
	    }
	    tx.commit();
	    session.close();
		return output;
	}
}
