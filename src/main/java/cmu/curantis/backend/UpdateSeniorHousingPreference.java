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
 * The resource that updates the senior housing preference information.
 * 
 * Input fields: circleId, preference
 * Input example: {"circleId":1,"preference":"{\"price\":\"150-200\"}"}
 * 
 * Output fileds: message, success
 * Output example:
 * {
    "message": "Preference updated!",
    "success": true
   }
 * @author jingzhu
 *
 */
@Path("/seniorhousing/updatePreference")
public class UpdateSeniorHousingPreference {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public  SeniorHousingPrfOutput add(SeniorHousingPrfInput input) {
		SeniorHousingPrfOutput output = new SeniorHousingPrfOutput();
		
		SeniorHousingCircleDAO dao = new SeniorHousingCircleDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        
        SeniorHousingCircleBean bean = new SeniorHousingCircleBean();
        bean.setCircleId(input.getCircleId());
        bean.setPreference(input.getPreference());
        if (dao.updatePreference(session, bean)) {
        	output.setSuccess(true);
        	output.setMessage("Preference updated!");
        } else {
        	output.setSuccess(false);
        	output.setMessage("Error!");
        }
        
        tx.commit();
        session.close();
		
		return output;
	}
}
