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
import cmu.curantis.inputbeans.LovedOneInput;
import cmu.curantis.outputbeans.CircleOutput;
/**
 * The resource that deals with creating a care team(circle).
 * 
 * Input fields: email, circleName, triggerEvent, natureOfRel, geoRel, lovedone_firstName, lovedone_LastName, pictureUrl, lovedoneAddress
 * Input example:
 * {
 * "email":"john@gmail.com", 
 * "circleName":"John Banta",
 * "triggerEvent":"Heart Attack",
 * "natureOfRel":"Son",
 * "geoRel":"Local",
 * "lovedone_firstName":"Carl",
 * "lovedone_LastName":"Banta",
 * "pictureUrl":"www.s3.com",
 * "lovedoneAddress":"Greater Chicago Area",
 * }
 * 
 * Output fields: circleId, success, message
 * Output example:
 * {
 * "circleId": 1,
 * "success": true,
 * "message": "Circle created!"
 * }
 * 
 * @author junyisong
 *
 */
@Path("/createcircle")
public class CreateCircle {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public CircleOutput createCircle(LovedOneInput input) {
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
	    
	    //check if circle name is unique
	    boolean circleexists = caregiverCircleDAO.circleExists(session, input.getCircleName());
	    if(circleexists) {
	    	output.setMessage("Circle name exists!");
	        output.setSuccess(false);
	        return output;
	    }
	   
	    CaregiverCircleBean caregiverCircle = new CaregiverCircleBean();
	    CircleSubsBean subsb =new CircleSubsBean();
	    subsb.setLovedone_firstName(input.getLovedone_firstName());
	    subsb.setLovedone_LastName(input.getLovedone_LastName());
	    subsb.setLovedoneAddress(input.getLovedoneAddress());
	    subsb.setPictureUrl(input.getPictureUrl());
	    
	    long circleId = circleSubsDAO.addCircleSubsLoved(session, subsb);
	    caregiverCircle.setIdentity();
	    caregiverCircle.getIdentity().setCircleID(circleId);
	    caregiverCircle.getIdentity().setEmail(input.getEmail());
	    caregiverCircle.setCirclename(input.getCircleName());
	    caregiverCircle.setPrimaryCaregiver(true);
	    caregiverCircle.setGeorelationship(input.getGeoRel());
	    caregiverCircle.setJoinStatus(true);
	    caregiverCircle.setRelationshipNature(input.getNatureOfRel());
	    caregiverCircle.setTriggerEvent(input.getTriggerEvent());
	    boolean status = caregiverCircleDAO.create(session, caregiverCircle);
	    if (status) {
	        output.setCircleId(circleId);
	        output.setSuccess(true);
	        output.setMessage("Circle created!");
	    } else {
	        output.setMessage("Creating circle failed!");
	        output.setSuccess(false);
	    }
		
		tx.commit();
		session.close();
		return output;
	}
}




