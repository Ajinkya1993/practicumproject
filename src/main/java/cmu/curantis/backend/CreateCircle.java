package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.CaregiverInfoDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleOutput;

@Path("/createcircle")
public class CreateCircle {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public CircleOutput createCircle(CircleInput input) {
	    CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
	    Session session = SessionUtil.getSession();
	    
	    Transaction tx = session.beginTransaction();
	    CaregiverCircleBean caregiverCircle = new CaregiverCircleBean();
	    caregiverCircleDAO.create(session, caregiverCircle);
	    // A caregiver can be create multiple circles?
	    
		System.out.println(input.getCircleName());
		CircleOutput output = new CircleOutput();
		output.setCircleId(1);
		output.setSuccess(true);
		output.setMessage("Circle created!");
		return output;
	}
}
