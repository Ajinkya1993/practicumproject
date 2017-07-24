package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.CircleSubsDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleOutput;

@Path("/createcircle")
public class CreateCircle {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public CircleOutput createCircle(CircleInput input) {
	    CircleOutput output = new CircleOutput();
	    if (input.getCircleName() == null || input.getCircleName().length() == 0
	            || input.getEmail() == null || input.getEmail().length() == 0) {
	        output.setMessage("Missing circle name or email!");
	        output.setSuccess(false);
	        return output;
	    }
	    CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
	    CircleSubsDAO circleSubsDAO = new CircleSubsDAO();
	    Session session = SessionUtil.getSession(); 
	    Transaction tx = session.beginTransaction();
	    
	    CaregiverCircleBean caregiverCircle = new CaregiverCircleBean();
	    long circleId = circleSubsDAO.addCircleSubs(session, input.getCircleName());
	    caregiverCircle.setIdentity();
	    caregiverCircle.getIdentity().setCircleID(circleId);
	    caregiverCircle.getIdentity().setEmail(input.getEmail());
	    caregiverCircle.setCirclename(input.getCircleName());
	    caregiverCircle.setPrimaryCaregiver(true);
	    caregiverCircle.setGeorelationship(input.getGeoRel());
	    caregiverCircle.setJoinStatus(true);
	    caregiverCircle.setRelationshipNature(input.getNatureOfRel());
	    caregiverCircle.setTriggerEvent(input.getTriggerEvent());
	    caregiverCircleDAO.create(session, caregiverCircle);
		output.setCircleId(circleId);
		output.setSuccess(true);
		output.setMessage("Circle created!");
		
		tx.commit();
		session.close();
		return output;
	}
}
