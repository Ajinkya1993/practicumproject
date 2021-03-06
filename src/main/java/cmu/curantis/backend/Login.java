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

@Path("/login")
public class Login {
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public LoginOutput login(LoginInput input) {
		LoginOutput output = new LoginOutput();
		
		if (input.getEmail() == null || input.getEmail().length() == 0 || input.getPassword() == null || input.getPassword().length() == 0) {
			output.setMessage("Missing email or password!");
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
        	output.setMessage("Account doesn't exist!");
        	output.setSuccess(false);
        } else if (!caregiver.getPassword().equals(input.getPassword())) {
        	output.setMessage("Incorrect password!");
        	output.setSuccess(false);
        } else {
        	output.setFirstName(caregiver.getFirstName());
        	output.setLastName(caregiver.getLastName());
        	output.setMessage("Login success!");
        	output.setSuccess(true);
        }
        tx.commit();
        session.close();
		
		return output;
	}
}
