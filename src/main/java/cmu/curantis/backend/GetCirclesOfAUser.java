package cmu.curantis.backend;

import java.awt.geom.Ellipse2D;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleListOutput;

@Path("/getcirclesofauser")
public class GetCirclesOfAUser {
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CircleListOutput getCirclesOfAUser(CircleInput input) {
        CircleListOutput output = new CircleListOutput();
        if (input == null || input.getEmail() == null || input.getEmail().length() == 0) {
            output.setMessage("Missing Email!");
            output.setSuccess(false);
            return output;
        }
        
        CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        
        List<CaregiverCircleBean> beans = caregiverCircleDAO.getByEmail(session, input.getEmail());
        if (beans == null) {
            output.setMessage("No circle for this caregiver!");
            output.setSuccess(false);
        } else {
            JsonArray array = new JsonArray();
            for (CaregiverCircleBean circle : beans) {
                JsonObject obj = new JsonObject();
                obj.addProperty("circleId", circle.getIdentity().getCircleID());
                obj.addProperty("circleName", circle.getCirclename());
                array.add(obj);
            }
            output.setMessage("These are the circles this caregiver is in!");
            output.setList(array);
            output.setSuccess(true);
        }
        
        tx.commit();
        session.close();
        return output;
        
    }
    
}
