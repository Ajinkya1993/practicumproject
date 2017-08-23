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
import cmu.curantis.outputbeans.LovedOneOutput;

@Path("/viewlovedoneinfo")
public class ViewLovedoneInfo {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public LovedOneOutput viewLovedoneInfo(CircleInput input) {
		System.out.println("Fist line debug: email and circle name "+input.getEmail() +" " +input.getCircleName());
	    LovedOneOutput output = new LovedOneOutput();
	    if (input.getCircleName() == null || input.getCircleName().length() == 0
	            || input.getEmail() == null || input.getEmail().length() == 0) {
	        output.setMessage("Missing circle name or email!");
	        output.setSuccess(false);
	        return output;
	    }
	    String email = input.getEmail();
	    String circleName = input.getCircleName();
	    Long circleId;
	    //Long circleId = input.getCircleId();
	    
	    CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
	    CircleSubsDAO circleSubsDAO = new CircleSubsDAO();
	    
	    Session session = SessionUtil.getSession(); 
	    Transaction tx = session.beginTransaction();
	    
	   //or can be email and id
	    CaregiverCircleBean caregiverCircle = caregiverCircleDAO.getByEmailAndName(session, email, circleName);
	    CircleSubsBean csubstemp = new CircleSubsBean();
	    circleId = caregiverCircle.getIdentity().getCircleID();
	    csubstemp.setCircleId(circleId);
	    //CircleSubsBean csubs = circleSubsDAO.getCircleSubs(session, csubstemp);
	    CircleSubsBean csubs = new CircleSubsBean();
	    csubs.setCircleId(circleId);
        output.setCircleId(circleId);
        System.out.println("The circleId IS COMING OUT TO BE "+circleId);
        output.setCirclename(circleName);
        output.setGeorelationship(caregiverCircle.getGeorelationship());
        output.setPrimaryCaregiver(caregiverCircle.getPrimaryCaregiver());
        output.setRelationshipNature(caregiverCircle.getRelationshipNature());
       output.setTriggerEvent(caregiverCircle.getTriggerEvent());
       output.setCircleId(caregiverCircle.getIdentity().getCircleID());
       
        //output.setSubscribedServices("Housing Service > Ancillary Service");
        CircleSubsBean cic_op = circleSubsDAO.getCircleSubs(session, csubs);
        
        
        output.setPictureUrl(cic_op.getPictureUrl());
        if(cic_op.getPictureUrl() == null) {
        	output.setPictureUrl("www.abc.com");
        }
        	output.setLovedoneAddress(cic_op.getLovedoneAddress());	
        
        	output.setLovedone_firstName(cic_op.getLovedone_firstName());	
        
        	output.setLovedone_LastName(cic_op.getLovedone_LastName());
        
      
            	output.setSubscribedServices(cic_op.getServicesSubscribed());
            
        /*
        output.setLovedURL(csubs.getPictureUrl());
        output.setSubscribedServices(csubs.getServicesSubscribed());
        output.setLovedAddress(csubs.getLovedoneAddress());
        output.setLovedoneFname(csubs.getLovedone_firstName());
        output.setLovedoneLname(csubs.getLovedone_LastName());*/
	   //error cases would be checked while filling in loved one's information
	    output.setMessage("Viewing LovedOne Info successfully!");
	    output.setSuccess(true);	   
		
		tx.commit();
		session.close();
		return output;
	}
}
