package cmu.curantis.backend;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleOutput;

@Path("/joincircle")
public class JoinCircle {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public CircleOutput joinCircle(CircleInput input) {
	    CaregiverCircleDAO caregiverCircleDAO = new CaregiverCircleDAO();
	    Session session = SessionUtil.getSession();
	    Transaction tx = session.beginTransaction();
	    CircleOutput output = new CircleOutput();
	   
	    CaregiverCircleBean curCircle = new CaregiverCircleBean();
	    List<CaregiverCircleBean> circleList = caregiverCircleDAO.read(session, curCircle);
	    if (circleList == null || circleList.size() == 0) {
	        output.setMessage("No circle!");
	        output.setSuccess(false);
	    } else {
	        curCircle = circleList.get(0);
	        if (curCircle.getJoinStatus()) {
	            output.setMessage("Joined already!");
	            output.setSuccess(false);
	        } else {
	            curCircle.setGeorelationship(input.getGeoRel());
	            curCircle.setRelationshipNature(input.getNatureOfRel());
	            curCircle.setJoinStatus(true);
	            caregiverCircleDAO.update(session, curCircle);
	            //Should also return the list of caregiver in the circle in output.
	            output.setCircleId(input.getCircleId());
	            output.setSuccess(true);
	            output.setMessage("Circle joined!");
	        }
	    }
	    tx.commit();
	    session.close();
		return output;
	}
}
