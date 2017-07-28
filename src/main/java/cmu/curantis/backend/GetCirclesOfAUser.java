package cmu.curantis.backend;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleListOutput;

@Path("/getcirclesofauser")
public class GetCirclesOfAUser {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CircleListOutput getCirclesOfAUser(CircleInput input) throws JSONException {
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
            JSONArray array = new JSONArray();
            for (CaregiverCircleBean circle : beans) {
                JSONObject obj = new JSONObject();
                obj.put("circleId", circle.getIdentity().getCircleID());
                obj.put("circleName", circle.getCirclename());
                array.put(obj);
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
