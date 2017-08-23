package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.CaregiverInfoDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.inputbeans.LoginInput;
import cmu.curantis.outputbeans.LoginOutput;

@Path("/googleLogin")
public class GoogleLogin {

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public LoginOutput login(LoginInput input) {
		
		LoginOutput output = new LoginOutput();
		
		if (input.getEmail() == null || input.getEmail().length() == 0) {
			output.setMessage("Missing email");
        	output.setSuccess(false);
        	return output;
		}
		
		CaregiverInfoDAO caregiverdao = new CaregiverInfoDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        CaregiverInfoBean bean = new CaregiverInfoBean();
        
        bean.setEmail(input.getEmail());
        CaregiverInfoBean caregiver = caregiverdao.getCaregiverInfo(session, bean);
        if (caregiver == null || !caregiver.getRegisteredStatus()) {
        	//System.out.println("No emailId found in backend");
        	output.setMessage("Account doesn't exist!");
        	output.setSuccess(false);
        }
        tx.commit();
        session.close();
		
		return output;
	}
	
	

}
