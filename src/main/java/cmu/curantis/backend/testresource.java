package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.CaregiverInfoDAO;
import cmu.curantis.dao.CircleSubsDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.entities.CircleSubsBean;


@Path("/testAjinkya")
public class testresource {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/testdelete")
	public void testdelete () {
		
		CircleSubsBean bean = new CircleSubsBean();
		bean.setCardMemberFirstName("Shubham");
		bean.setCardMemberLastName("Nimbalkar");
		bean.setCircleId(3);
		bean.setCircleName("mycircle");
		CircleSubsDAO dao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        dao.deleteCircleSubs(session, bean);
                
        tx.commit();
        session.close();
		
		
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/testadd")
	public void testadd () {
		
		CircleSubsBean bean = new CircleSubsBean();
		bean.setCardMemberFirstName("Shubham");
		bean.setCardMemberLastName("Nimbalkar");
		bean.setCircleId(1);
		bean.setCircleName("mycircle");
		CircleSubsDAO dao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        System.out.println(dao.addCircleSubs(session, bean));                
        tx.commit();
        session.close();
		
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/testget")
	public void testget () {
		
		CircleSubsBean bean = new CircleSubsBean();
		bean.setCardMemberFirstName("Shubham");
		bean.setCardMemberLastName("Nimbalkar");
		bean.setCircleId(2);
		bean.setCircleName("mycircle");
		CircleSubsDAO dao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        System.out.println(dao.getCircleSubs(session, bean).getCardMemberFirstName());                
        tx.commit();
        session.close();
		
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/testupdate")
	public void testupdate () {
		
		CircleSubsBean bean = new CircleSubsBean();
		bean.setCardMemberFirstName("Ajinkya");
		bean.setCardMemberLastName("Nimbalkar");
		bean.setCircleId(3);
		bean.setCircleName("mycircle");
		CircleSubsDAO dao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        System.out.println(dao.updateCircleSubs(session, bean));
        
        tx.commit();
        session.close();
		
	}
}
