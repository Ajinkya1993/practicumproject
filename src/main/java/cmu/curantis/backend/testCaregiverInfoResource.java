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

@Path("/caregiver")
public class testCaregiverInfoResource {

		@POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
		public void test () {
			
			CaregiverInfoDAO dao = new CaregiverInfoDAO();
			CaregiverInfoBean bean = new CaregiverInfoBean();
			bean.setEmail("ns8194@gmail.com");
			Session session = SessionUtil.getSession();        
	        Transaction tx = session.beginTransaction();
	        System.out.println(dao.deleteCaregiverInfo(session, bean));
	        tx.commit();
	        session.close();
			
			/*CaregiverInfoDAO dao = new CaregiverInfoDAO();
			CaregiverInfoBean bean = new CaregiverInfoBean();
			bean.setEmail("ns8194@gmail.com");
			bean.setFirstName("Akash");
			Session session = SessionUtil.getSession();        
	        Transaction tx = session.beginTransaction();
	        System.out.println(dao.updateCaregiverInfo(session, bean));
	        tx.commit();
	        session.close();*/
			
			/*CaregiverInfoDAO dao = new CaregiverInfoDAO();
			CaregiverInfoBean bean = new CaregiverInfoBean();
			bean.setEmail("ns8194@gmail.com");
			Session session = SessionUtil.getSession();        
	        Transaction tx = session.beginTransaction();
	        System.out.println(dao.getCaregiverInfo(session, bean));
	        tx.commit();
	        session.close();*/
	        
			/*CaregiverInfoBean bean = new CaregiverInfoBean();
			bean.setEmail("ns8194@gmail.com");
			bean.setFirstName("Namita");
			bean.setLastName("Sibal");
			bean.setPassword("namitas");
			bean.setAddress("4350, Murray Avenue");
			bean.setPhoneNumber("4124822923");
			bean.setRegisteredStatus(true);
			CaregiverInfoDAO dao = new CaregiverInfoDAO();
			Session session = SessionUtil.getSession();        
	        Transaction tx = session.beginTransaction();
	        System.out.println(dao.addCaregiverInfo(session, bean));        
	        tx.commit();
	        session.close();*/
		}
		

}
