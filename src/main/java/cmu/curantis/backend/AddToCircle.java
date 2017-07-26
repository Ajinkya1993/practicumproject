package cmu.curantis.backend;

import java.awt.geom.Ellipse2D;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.CaregiverInfoDAO;
import cmu.curantis.dao.CircleSubsDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.entities.CircleSubsBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleOutput;

@Path("/addToCircle")
public class AddToCircle {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CircleOutput addToCircle(CircleInput input) {
        CircleOutput output = new CircleOutput();
        if (input.getCircleId() == 0 || input.getEmail() == null || input.getEmail().length() == 0
                || input.getEmailToAdd() == null || input.getEmailToAdd().length() == 0) {
            output.setMessage("Missing email or circleId!");
            output.setSuccess(false);
            return output;
        } 
        CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO(); 
        CaregiverInfoDAO caregiverInfoDAO =  new CaregiverInfoDAO();
        CircleSubsDAO circleSubsDAO = new CircleSubsDAO();
        
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        
        CaregiverCircleBean circleBean = caregiverCircleDAO.getByEmailAndId(session, input.getEmailToAdd(), input.getCircleId());
        CaregiverCircleBean curCG = caregiverCircleDAO.getByEmailAndId(session, input.getEmail(), input.getCircleId());
        CaregiverInfoBean cgToAdd = new CaregiverInfoBean();
        cgToAdd.setEmail(input.getEmailToAdd());
        CaregiverInfoBean caregiverInfoBean = caregiverInfoDAO.getCaregiverInfo(session, cgToAdd);
        CircleSubsBean subsBean = new CircleSubsBean();
        subsBean.setCircleId(input.getCircleId());
        CircleSubsBean circleSubsBean = circleSubsDAO.getCircleSubs(session, subsBean);
        
        if (circleSubsBean == null) {  //Check if the circleId exists
            output.setMessage("Wrong circleId!");
            output.setSuccess(false);
        } else if (curCG == null || !curCG.getPrimaryCaregiver()) { // Check if the current user is the primary caregiver for this circle
            output.setMessage("Not primary caregiver for this circle!");
            output.setSuccess(false);
        } else if (circleBean != null) { //Check if the invited person has already joined the circle
            output.setMessage("Member already exists in this circle!");
            output.setSuccess(false); 
        } else {
            //Update the circle table
            CaregiverCircleBean newCaregiver = new CaregiverCircleBean();
            newCaregiver.setIdentity();
            newCaregiver.getIdentity().setEmail(input.getEmailToAdd());
            newCaregiver.getIdentity().setCircleID(input.getCircleId());
            newCaregiver.setCirclename(curCG.getCirclename());
            newCaregiver.setPrimaryCaregiver(false);
            newCaregiver.setTriggerEvent(curCG.getTriggerEvent());
            newCaregiver.setJoinStatus(false);
            boolean status = caregiverCircleDAO.create(session, newCaregiver);
            if (status) {
                output.setCircleId(input.getCircleId());
                output.setMessage("Added to circle!");
                output.setSuccess(true);
                if (caregiverInfoBean == null) { //Check if the invited persion exists in the caregiver info table
                    //Add to the caregiver info table
                    cgToAdd.setRegisteredStatus(false);
                    caregiverInfoDAO.addCaregiverInfo(session, cgToAdd);
                }
            } else {
                output.setMessage("Adding to circle failed!");
                output.setCircleId(input.getCircleId());
                output.setSuccess(false);
            }
            
        }
        
        tx.commit();
        session.close();
        return output;
    }
}
