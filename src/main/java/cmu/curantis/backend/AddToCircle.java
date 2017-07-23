package cmu.curantis.backend;

import java.util.List;

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

@Path("/addToCircle")
public class AddToCircle {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CircleOutput addToCircle(CircleInput input) {
        CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO(); 
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        CircleOutput output = new CircleOutput();
        //First check if this email already exists in this circle
        List<CaregiverCircleBean> members = caregiverCircleDAO.getByCircleId(session, input.getCircleId());
        for (CaregiverCircleBean c : members) {
            if (c.getIdentity().getCircleID() == input.getCircleId()) {
                output.setMessage("Member already exists in this circle!");
                output.setSuccess(false);
                return output;
            }
        }
        CaregiverCircleBean newCaregiver = new CaregiverCircleBean();
        newCaregiver.getIdentity().setCircleID(input.getCircleId());
        newCaregiver.setCirclename(input.getCircleName());
        newCaregiver.setGeorelationship(input.getGeoRel());
        newCaregiver.setPrimaryCaregiver(false);
        newCaregiver.setRelationshipNature(input.getNatureOfRel());
        newCaregiver.setTriggerEvent(input.getTriggerEvent());
        newCaregiver.setJoinStatus(false);
        caregiverCircleDAO.create(session, newCaregiver);
        
        output.setMessage("Added to circle!");
        output.setSuccess(true);
        return output;
    }
}
