package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.SeniorHousingCircleDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.SeniorHousingCircleBean;
import cmu.curantis.inputbeans.SeniorHousingPrfInput;
import cmu.curantis.outputbeans.SeniorHousingPrfOutput;
/**
 * The resource that fetch the senior housing preference information of a care team.
 * 
 * Input fields: circleId
 * Input example: {"circleId":1}
 * 
 * Output fields: message, preference, success
 * Output example: 
 * {
    "message": "Get preference successful!",
    "preference": "{\"location\":\"Pittsburgh\",\"price\":\"100-200\"}",
    "success": true
   }
 * @author curantisTeamCMU
 *
 */
@Path("/seniorhousing/getPreference")
public class GetSeniorHousingPreference {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public  SeniorHousingPrfOutput add(SeniorHousingPrfInput input) {
		SeniorHousingPrfOutput output = new SeniorHousingPrfOutput();
		
		SeniorHousingCircleDAO dao = new SeniorHousingCircleDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        
        SeniorHousingCircleBean bean = dao.getPreference(session, input.getCircleId());
        if (bean == null) {
            output.setSuccess(false);
            output.setMessage("No preference in this circle!");
        } else {
        	output.setPreference(bean.getPreference());
        	output.setSuccess(true);
            output.setMessage("Get preference successful!");
        }
        
        
        tx.commit();
        session.close();
		
		return output;
	}
}
