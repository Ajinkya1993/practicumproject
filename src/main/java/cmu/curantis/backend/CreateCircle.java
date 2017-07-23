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
import cmu.curantis.entities.CircleSubsBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleOutput;

@Path("/createcircle")
public class CreateCircle {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public CircleOutput createCircle(CircleInput input) {
	    CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
	    CircleSubsDAO circleSubsDAO = new CircleSubsDAO();
	    Session session = SessionUtil.getSession(); 
	    Transaction tx = session.beginTransaction();
	    
	    CaregiverCircleBean caregiverCircle = new CaregiverCircleBean();
	    CircleSubsBean circleSubsBean = new CircleSubsBean();
	    circleSubsBean.setCircleName(input.getCircleName());
	    circleSubsDAO.addCircleSubs(session, circleSubsBean);
	    // A caregiver can be create multiple circles?
	    // Check if the circle is unique? by circle name?
	    // CircleId is generated by us, can't set circleId here (tho CircleInput has circleId)?
	    caregiverCircle.getIdentity().setEmail(input.getEmail());
	    caregiverCircle.setCirclename(input.getCircleName());
	    caregiverCircle.setPrimaryCaregiver(true);
	    caregiverCircle.setGeorelationship(input.getGeoRel());
	    caregiverCircle.setJoinStatus(true);
	    caregiverCircle.setRelationshipNature(input.getNatureOfRel());
	    caregiverCircle.setTriggerEvent(input.getTriggerEvent());
	    caregiverCircleDAO.create(session, caregiverCircle);
		//System.out.println(input.getCircleName());
		CircleOutput output = new CircleOutput();
		output.setCircleId(1);
		output.setSuccess(true);
		output.setMessage("Circle created!");
		
		tx.commit();
		session.close();
		return output;
	}
}
