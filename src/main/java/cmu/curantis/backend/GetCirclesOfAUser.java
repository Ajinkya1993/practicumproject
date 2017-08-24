package cmu.curantis.backend;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleInfo;
import cmu.curantis.outputbeans.CircleListOutput;
/**
 * The resource that reals with getting all the circles that a user is in.
 * 
 * Input fields: email
 * Input example:
 * {
 * "email": "john@gmail.com"
 * }
 * 
 * Output fields: message, list, success
 * Output example:
 * {
 * "message": ""These are the circles this caregiver is in!",
 * "list": [
        {
            "circleId": 1,
            "circleName": "Maxine Banta"
        },
        {
            "circleId": 2,
            "circleName": "Tony Maushart"
        }
    ],
 * "success": true
 * }
 * @author curantisTeamCMU
 *
 */
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
        
        List<CaregiverCircleBean> beans = caregiverCircleDAO.getByEmailAndJoined(session, input.getEmail());
        if (beans == null) {
            output.setMessage("No circle for this caregiver!");
            output.setSuccess(false);
        } else {
            List<CircleInfo> circleList = new ArrayList<CircleInfo>();
            for (CaregiverCircleBean circle : beans) {
                CircleInfo obj = new CircleInfo();
                obj.setCircleId(circle.getIdentity().getCircleID());
                obj.setCircleName(circle.getCirclename());
                circleList.add(obj);
            }
            output.setMessage("These are the circles this caregiver is in!");
            output.setList(circleList);
            output.setSuccess(true);
        }
        
        tx.commit();
        session.close();
        return output;
        
    }
    
}
