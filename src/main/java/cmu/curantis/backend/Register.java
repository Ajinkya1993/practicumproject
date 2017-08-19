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
import cmu.curantis.inputbeans.RegisterInput;
import cmu.curantis.outputbeans.LoginOutput;
/**
 * The resource that deals with register actions.
 * 
 * Input fields: email, firstName, middleName, lastName, password, address, phoneNo
 * Input example:
 * {
 *  "email":"j@gmail.com",
 *  "firstName":"John",
 *  "lastName":"Smith",
 *  "password":"123",
 *  "address":"5000 Forbes Ave",
 *  "phoneNo":"1234567"
 * }
 * 
 * Output fields: email, firstName, lastName, message, success
 * Output example:
 * {
    "email": "j@gmail.com",
    "firstName": "John",
    "lastName": "Smith",
    "message": "Register success!",
    "success": true
   }
 * 
 * @author jingzhu
 *
 */
@Path("/register")
public class Register {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public LoginOutput register(RegisterInput input) {
		LoginOutput output = new LoginOutput();
		
		if (input.getEmail() == null || input.getEmail().length() == 0 || input.getPassword() == null || input.getPassword().length() == 0) {
			output.setMessage("Missing email or password!");
        	output.setSuccess(false);
        	return output;
		}
		
		CaregiverInfoDAO caregiverdao = new CaregiverInfoDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        CaregiverInfoBean caregiver = new CaregiverInfoBean();
    	caregiver.setEmail(input.getEmail());
    	caregiver.setFirstName(input.getFirstName());
    	caregiver.setMiddleName(input.getMiddleName());
    	caregiver.setLastName(input.getLastName());
    	caregiver.setPassword(input.getPassword());
    	caregiver.setAddress(input.getAddress());
    	caregiver.setPhoneNumber(input.getPhoneNo());
    	caregiver.setRegisteredStatus(true);
        if (caregiverdao.addCaregiverInfo(session, caregiver)) {
        	output.setFirstName(input.getFirstName());
        	output.setLastName(input.getLastName());
        	output.setMessage("Register success!");
        	output.setEmail(input.getEmail());
        	output.setSuccess(true);
        } else {
        	output.setMessage("Account already exist!");
        	output.setSuccess(false);
        }
        tx.commit();
        session.close();
		
		return output;
	}
}
