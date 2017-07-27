package cmu.curantis.backend;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.CaregiverInfoDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleListOutput;

@Path("/getusersofacircle")
public class GetUsersOfACircle {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CircleListOutput getUsersOfACircle(CircleInput input) {
        CircleListOutput output = new CircleListOutput();
        if (input == null || input.getCircleId() == 0) {
            output.setMessage("Missing circleId!");
            output.setSuccess(false);
            return output;
        }
        CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
        CaregiverInfoDAO caregiverInfoDAO = new CaregiverInfoDAO();
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        
        List<CaregiverCircleBean> beans = caregiverCircleDAO.getByCircleId(session, input.getCircleId());
        if (beans == null) {
            output.setMessage("No caregiver in this circle!");
            output.setSuccess(false);
        } else {
            JsonArray array = new JsonArray();
            for (CaregiverCircleBean circleBean : beans) {
                JsonObject obj = new JsonObject();
                String email = circleBean.getIdentity().getEmail();
                obj.addProperty("email", email);
                
                CaregiverInfoBean queryBean = new CaregiverInfoBean();
                queryBean.setEmail(email);
                CaregiverInfoBean infoBean = caregiverInfoDAO.getCaregiverInfo(session, queryBean);
                if (infoBean == null) {
                    output.setMessage("User not in caregiver info table!");
                    output.setSuccess(false);
                } else {
                    obj.addProperty("First Name", infoBean.getFirstName());
                    obj.addProperty("Middle Name", infoBean.getMiddleName());
                    obj.addProperty("Last Name", infoBean.getLastName());
                }
                array.add(obj);
            }
            output.setList(array);
            output.setMessage("These are caregivers in this circle!");
            output.setSuccess(true);
        }
        
        tx.commit();
        session.close();
        return output;
    }
}
