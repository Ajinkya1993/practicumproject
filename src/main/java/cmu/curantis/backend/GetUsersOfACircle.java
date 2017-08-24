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
import cmu.curantis.dao.CaregiverInfoDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.UserListOutput;
import cmu.curantis.outputbeans.UserOfCircle;
/**
 * The resource that delas with getting all the members in a care team
 * 
 * Input fields: circleId
 * Input example:
 * {
 * "circleId": 1
 * }
 * 
 * Output fields: list, message, success
 * Output example:
 * {
 * "list": [
        {
            "email": "barry@gmail.com",
            "firstName": "Barry",
            "lastName": "Siegel",
            "middleName": null,
            "natureOfRel": "Lawyer",
            "phoneNumber": "2122016790",
            "primaryCaregiver": false
        },
        {
            "email": "john@gmail.com",
            "firstName": "John",
            "lastName": "Banta",
            "middleName": null,
            "natureOfRel": "Son",
            "phoneNumber": "3122976560",
            "primaryCaregiver": true
        }
    ],
  "message": "These are caregivers in this circle!",
  "success": true
 * }
 * @author curantisTeamCMU
 *
 */
@Path("/getusersofacircle")
public class GetUsersOfACircle {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserListOutput getUsersOfACircle(CircleInput input) throws JSONException {
        UserListOutput output = new UserListOutput();
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
            List<UserOfCircle> userList = new ArrayList<UserOfCircle>();
            for (CaregiverCircleBean circleBean : beans) {
                if (!circleBean.getJoinStatus()) {
                    continue;
                }
                UserOfCircle obj = new UserOfCircle();
                String email = circleBean.getIdentity().getEmail();
                obj.setEmail(email);
                
                CaregiverInfoBean queryBean = new CaregiverInfoBean();
                queryBean.setEmail(email);
                CaregiverInfoBean infoBean = caregiverInfoDAO.getCaregiverInfo(session, queryBean);
                if (infoBean == null) {
                    output.setMessage("User not in caregiver info table!");
                    output.setSuccess(false);
                } else {
                	obj.setEmail(email);
                    obj.setFirstName(infoBean.getFirstName());
                    obj.setMiddleName(infoBean.getMiddleName());
                    obj.setLastName(infoBean.getLastName());
                    obj.setPhoneNumber(infoBean.getPhoneNumber());
                    obj.setNatureOfRel(circleBean.getRelationshipNature());
                    obj.setPrimaryCaregiver(circleBean.getPrimaryCaregiver());   
                }
                userList.add(obj);
            }
            output.setList(userList);
            output.setMessage("These are caregivers in this circle!");
            output.setSuccess(true);
        }
        
        tx.commit();
        session.close();
        return output;
    }
}
