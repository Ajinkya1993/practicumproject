package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.SessionUtil;
import cmu.curantis.dao.caregiverCircleDAO;
import cmu.curantis.entities.CaregiverCircleBean;

@Path("/testvarun")
public class TestCaregiverCircleBean {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/testdelete")
	public void testdelete () {
		
		CaregiverCircleBean ub = new CaregiverCircleBean();
		ub.setCirclename("varunscircle");
		ub.setGeorelationship("Uncle");
		ub.setJoinStatus(true);
		ub.setPrimaryCaregiver(true);
		ub.setRelationshipNature("SON");
		ub.setTriggerEvent("illness");
		ub.setIdentity();
		ub.getIdentity().setCircleID(1);
		ub.getIdentity().setEmail("varunp@andrew.cmu.edu");
		caregiverCircleDAO udao = new caregiverCircleDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        udao.delete(session, ub);
                
        tx.commit();
        session.close();
		
		
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/testadd")
	public void testadd () {
		
		CaregiverCircleBean ub = new CaregiverCircleBean();
		ub.setCirclename("varunscircle");
		ub.setGeorelationship("Uncle");
		ub.setJoinStatus(true);
		ub.setPrimaryCaregiver(true);
		ub.setRelationshipNature("SON");
		ub.setTriggerEvent("illness");
		ub.setIdentity();
		ub.getIdentity().setCircleID(1);
		ub.getIdentity().setEmail("varunp@andrew.cmu.edu");
		caregiverCircleDAO udao = new caregiverCircleDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        System.out.println(udao.create(session, ub));                
        tx.commit();
        session.close();
		
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/testget")
	public void testget () {
		
		CaregiverCircleBean ub = new CaregiverCircleBean();
		ub.setCirclename("varunscircle");
		ub.setGeorelationship("Uncle");
		ub.setJoinStatus(true);
		ub.setPrimaryCaregiver(true);
		ub.setRelationshipNature("SON");
		ub.setTriggerEvent("illness");
		ub.setIdentity();
		ub.getIdentity().setCircleID(1);
		ub.getIdentity().setEmail("varunp@andrew.cmu.edu");
		caregiverCircleDAO udao = new caregiverCircleDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        System.out.println(udao.getByCircleId(session, ub.getIdentity().getCircleID()));                
        tx.commit();
        session.close();
		
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/testupdate")
	public void testupdate () {
		
		CaregiverCircleBean ub = new CaregiverCircleBean();
		ub.setCirclename("varunscircle");
		ub.setGeorelationship("Uncle");
		ub.setJoinStatus(true);
		ub.setPrimaryCaregiver(true);
		ub.setRelationshipNature("NEPHEW");
		ub.setTriggerEvent("illness");
		ub.setIdentity();
		ub.getIdentity().setCircleID(1);
		ub.getIdentity().setEmail("varunp@andrew.cmu.edu");
		caregiverCircleDAO udao = new caregiverCircleDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        System.out.println(udao.update(session, ub));
        
        tx.commit();
        session.close();
		
	}
}

