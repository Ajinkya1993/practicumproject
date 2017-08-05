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
import cmu.curantis.outputbeans.LovedOneOutput;

@Path("/updatelovedoneinfodb")
public class UpdateLovedOneInfoDB {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public LovedOneOutput viewLovedoneInfo(LovedOneInput input) {
		System.out.println("updatelovedoneinfoDB");
	    LovedOneOutput output = new LovedOneOutput();
	    if (input.getCircleName() == null || input.getCircleName().length() == 0
	            || input.getEmail() == null || input.getEmail().length() == 0) {
	        output.setMessage("Missing circle name or email!");
	        output.setSuccess(false);
	        return output;
	    }
	    String email = input.getEmail();
	    String circleName = input.getCircleName();
	    Long circleId = input.getCircleId();
	    System.out.println("In updatelovedoneinfoDB: Email, circleName and circleId is "+email + " " + circleName + " " + circleId);
	    
	    CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
	    CircleSubsDAO circleSubsDAO = new CircleSubsDAO();
	    
	    Session session = SessionUtil.getSession(); 
	    Transaction tx = session.beginTransaction();
	    
	   //or can be email and id
	    CaregiverCircleBean caregiverCircle = caregiverCircleDAO.getByEmailAndName(session, email, circleName);
	    CircleSubsBean csubstemp = new CircleSubsBean();
	    csubstemp.setCircleId(circleId);
	    CircleSubsBean csubs = circleSubsDAO.getCircleSubs(session, csubstemp);
	   // CircleSubsBean csubs = new CircleSubsBean();
	    caregiverCircle.setGeorelationship(input.getGeoRel());
	    caregiverCircle.setPrimaryCaregiver(true);
	    caregiverCircle.setRelationshipNature(input.getNatureOfRel());
	    caregiverCircle.setTriggerEvent(input.getTriggerEvent());
	    System.out.println("Checking output before querying DB "+input.getGeoRel() + " " + input.getNatureOfRel() + " " + input.getTriggerEvent());
	    csubs.setPictureUrl("abcdefg");
	    csubs.setLovedoneAddress(input.getLovedoneAddress());
	    csubs.setLovedone_firstName(input.getLovedone_firstName());
	    csubs.setLovedone_LastName(input.getLovedone_LastName());
        Boolean status = caregiverCircleDAO.update(session, caregiverCircle);
        if(status == false) {
        	output.setMessage("Could not update LovedOne Info successfully!");
    	    output.setSuccess(true);
    	    return output;
        } 
        Boolean status_subs = circleSubsDAO.updateCircleSubs(session, csubs);
        if(status_subs == false) {
        	output.setMessage("Could not update LovedOne Info successfully!");
    	    output.setSuccess(true);
    	    return output;
        } 
        
	    output.setMessage("Updated LovedOne Info successfully!");
	    output.setSuccess(true);	   
		
		tx.commit();
		session.close();
		return output;
	}
}
