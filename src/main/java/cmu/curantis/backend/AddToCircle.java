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
        CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO(); 
        CaregiverInfoDAO caregiverInfoDAO =  new CaregiverInfoDAO();
        CircleSubsDAO circleSubsDAO = new CircleSubsDAO();
        
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        CircleOutput output = new CircleOutput();
        //First check if this email already exists in this circle
        CaregiverCircleBean circleBean = caregiverCircleDAO.getByEmailAndId(session, input.getEmail(), input.getCircleId());
        CaregiverInfoBean cgToAdd = new CaregiverInfoBean();
        cgToAdd.setEmail(input.getEmail());
        CaregiverInfoBean caregiverInfoBean = caregiverInfoDAO.getCaregiverInfo(session, cgToAdd);
        CircleSubsBean subsBean = new CircleSubsBean();
        CircleSubsBean circleSubsBean = circleSubsDAO.getCircleSubs(session, subsBean);
        
        //Check if the circleId exists
        if (circleSubsBean == null) {
            output.setMessage("Wrong circleId!");
            output.setSuccess(false);
        }
        //Check if the invited persion exists in the caregiver info table
        if (caregiverInfoBean == null) {
            //Add to the caregiver info table
            cgToAdd.setRegisteredStatus(false);
            caregiverInfoDAO.addCaregiverInfo(session, cgToAdd);
        }
        //Check if the invited person has already joined the circle
        if (circleBean != null) {
            output.setMessage("Member already exists in this circle!");
            output.setSuccess(false); 
        } else {
            //Update the circle table
            CaregiverCircleBean newCaregiver = new CaregiverCircleBean();
            newCaregiver.setIdentity();
            newCaregiver.getIdentity().setEmail(input.getEmail());
            newCaregiver.getIdentity().setCircleID(input.getCircleId());
            newCaregiver.setCirclename(input.getCircleName());
            newCaregiver.setGeorelationship(input.getGeoRel());
            newCaregiver.setPrimaryCaregiver(false);
            newCaregiver.setRelationshipNature(input.getNatureOfRel());
            newCaregiver.setTriggerEvent(input.getTriggerEvent());
            newCaregiver.setJoinStatus(false);
            boolean status = caregiverCircleDAO.create(session, newCaregiver);
            if (status) {
                output.setCircleId(input.getCircleId());
                output.setMessage("Added to circle!");
                output.setSuccess(true);
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
