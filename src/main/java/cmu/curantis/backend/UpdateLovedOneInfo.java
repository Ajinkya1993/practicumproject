/**
 * This resource allows the user to view loved one’s information before updating it.
 * 
 * Input fields: email, circleName
 * Input example:
 * {
 * "circleName":"John Banta",
 * "email":"john@gmail.com"}
 * 
 * Output fields: circleId, circlename, georelationship, lovedone_firstName, lovedone_LastName, lovedoneAddress,
 *  message,pictureUrl, primaryCaregiver, relationshipNature, subscribedServices, success, triggerEvent
 * {
 *   "circleId": 1,
 *   "circlename": "John Banta",
 *   "georelationship": "Local",
 *   "lovedone_firstName": "Carl",
 *   "lovedone_LastName": "Banta",
 *   "lovedoneAddress": "Greater Chicago Area",
 *   "message": "Viewing LovedOne Info successfully!",
 *   "pictureUrl": "abcdefg",
 *   "primaryCaregiver": true,
 *   "relationshipNature": "Son",
 *   "subscribedServices": "Vendor Service->Senior Housing",
 *   "success": true,
 *   "triggerEvent": "Heart Attack"
 * }
 * 
 * @author curantisTeamCMU
 *
 */

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

@Path("/updatelovedoneinfo")
public class UpdateLovedOneInfo {
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
	    Long circleId = input.getCircleId();
	    
	    CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
	    CircleSubsDAO circleSubsDAO = new CircleSubsDAO();
	    
	    Session session = SessionUtil.getSession(); 
	    Transaction tx = session.beginTransaction();
	    
	   //or can be email and id
	    CaregiverCircleBean caregiverCircle = caregiverCircleDAO.getByEmailAndName(session, email, circleName);
	    CircleSubsBean csubstemp = new CircleSubsBean();
	    csubstemp.setCircleId(caregiverCircle.getIdentity().getCircleID());
	    CircleSubsBean cic_op = circleSubsDAO.getCircleSubs(session, csubstemp);
        output.setCircleId(circleId);
        output.setCirclename(circleName);
        output.setGeorelationship(caregiverCircle.getGeorelationship());
        output.setPrimaryCaregiver(caregiverCircle.getPrimaryCaregiver());
        output.setRelationshipNature(caregiverCircle.getRelationshipNature());
        output.setTriggerEvent(caregiverCircle.getTriggerEvent());
        output.setCircleId(caregiverCircle.getIdentity().getCircleID());

           	output.setLovedoneAddress(cic_op.getLovedoneAddress());	
           	output.setLovedone_firstName(cic_op.getLovedone_firstName());
           	output.setLovedone_LastName(cic_op.getLovedone_LastName());
            output.setSubscribedServices(cic_op.getServicesSubscribed());

	   //error cases would be checked while filling in loved one's information
	    output.setMessage("Viewing LovedOne Info successfully!");
	    output.setSuccess(true);	   
		
		tx.commit();
		session.close();
		return output;
	}
}
