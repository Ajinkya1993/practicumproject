package cmu.curantis.backend;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;

import cmu.curantis.dao.CircleSubsDAO;
import cmu.curantis.dao.DocumentMgmtDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CircleSubsBean;
import cmu.curantis.entities.DocumentMgmtBean;
import cmu.curantis.inputbeans.DocumentInput;
import cmu.curantis.inputbeans.ServicesInput;
import cmu.curantis.outputbeans.CircleOutput;
import cmu.curantis.outputbeans.DocumentOutput;

@Path("/manageservices")
public class ManageServices {
	
	/*
	 * send the extra services to be added in the input bean as JSON Array.
	 * Input Fields: circleId (long), services (String)  - multiple services can be added in one request with the separator as “->”
	 * Output Fields: Fields: subscribedServices (String) - multiples services can be separated with “->”, success (Boolean), message (String)
	 */
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public CircleOutput addServices (ServicesInput input) throws JSONException {
		CircleOutput output = new CircleOutput();
		CircleSubsBean bean = new CircleSubsBean();
		bean.setCircleId(input.getCircleId());
		CircleSubsDAO dao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		String curr_services = dao.getServicesSubscribed(session,bean);
		
		StringBuffer new_services = new StringBuffer(input.getServices());
		if (new_services == null || new_services.length() == 0) {
			output.setSuccess(true);
			output.setMessage("No services provided to subscribe to. Done.");
			output.setSubscribedServices(curr_services);
			return output;
		}
		try {
		
			if (curr_services == null || curr_services.length() == 0) {
				bean.setServicesSubscribed(new_services.toString());
				boolean status = dao.updateServicesSubscribed(session, bean);
				if (!status) {
					output.setSuccess(false);
					output.setMessage("Circle ID does not exist");
					return output;
				}
				output.setSuccess(true);
				output.setMessage("successfully subscribed to services");
				output.setSubscribedServices(new_services.toString());
				return output;
			} else {
				StringBuffer more_services = new StringBuffer();
				more_services = new_services.append("->").append(curr_services);
				bean.setServicesSubscribed(more_services.toString());
				boolean status = dao.updateServicesSubscribed(session, bean);
				if (!status) {
					output.setSuccess(false);
					output.setMessage("Error. Could not subscribe to services.");
					return output;
				}
				output.setSuccess(true);
				output.setSubscribedServices(more_services.toString());
				output.setMessage("successfully subscribed to services");
				return output;
			} 
		} finally {
			tx.commit();
			session.close();
		}
	}
	
	
	/*
	 * send the services to be deleted in the input bean as JSON Array.
	 * Input Fields : circleId (long), services (String)  - multiple services can be unsubscribed in one request with the separator as “->”
	 * Output Fields: Fields: subscribedServices (String) - multiples services can be separated with “->”, success (Boolean), message (String)
	 */
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public CircleOutput deleteServices (ServicesInput input) throws JSONException {
		CircleOutput output = new CircleOutput();
		CircleSubsBean bean = new CircleSubsBean();
		bean.setCircleId(input.getCircleId());
		CircleSubsDAO dao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		String curr_services = dao.getServicesSubscribed(session,bean);
		StringBuffer new_services = new StringBuffer();
		Set<String> deletionSet = new HashSet<String>();
		if (input.getServices() == null || input.getServices().length() == 0) {
			output.setSuccess(true);
			output.setMessage("If Circle ID is valid -> successfully deleted services");
			output.setSubscribedServices(curr_services);
			return output;
		}
		String[] toDelete = input.getServices().split("->");
		for (int i=0; i<toDelete.length; i++) {
			deletionSet.add(toDelete[i]);
		}
		try {
			if (curr_services == null || curr_services.length() == 0) {
				output.setSuccess(true);
				output.setMessage("If Circle ID is valid -> successfully deleted services");
				output.setSubscribedServices(curr_services);
				return output;
			}
			String[] serviceArray = curr_services.split("->");
			for (int i=0; i<serviceArray.length; i++) {
				if (!deletionSet.contains(serviceArray[i])) {
					new_services.append(serviceArray[i]);
					deletionSet.add(serviceArray[i]);
					if (i != serviceArray.length-1) {
						new_services.append("->");
					}
				}
				
			}
			if (new_services.length() > 0 && new_services.charAt(new_services.length()-1) == '>') {
				new_services.delete(new_services.length()-2, new_services.length());
			}
			bean.setServicesSubscribed(new_services.toString());
			boolean status = dao.updateServicesSubscribed(session, bean);
			if (!status) {
				output.setSuccess(false);
				output.setMessage("Error. Could not delete services.");
				return output;
			}
			output.setSuccess(true);
			output.setMessage("Successfully deleted services");
			output.setSubscribedServices(new_services.toString());
			return output;
		} finally {
			tx.commit();
			session.close();
		}
		
	}
	
	/*
	 * This resource allows the user to know which services he is subscribed to
	 * Input Fields : circleId (long)
	 * Output Fields: subscribedServices (String) , success (Boolean), message (String)
	 */
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public CircleOutput getServices (ServicesInput input) throws JSONException {
		CircleOutput output = new CircleOutput();
		CircleSubsBean bean = new CircleSubsBean();
		bean.setCircleId(input.getCircleId());
		CircleSubsDAO dao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
		String curr_services = dao.getServicesSubscribed(session,bean);
		String services = new String();
		services = dao.getServicesSubscribed(session, bean);
		output.setSuccess(true);
		output.setMessage("Got subscribed services");
		output.setSubscribedServices(services);
		tx.commit();
		session.close();
		return output;
	}
}
