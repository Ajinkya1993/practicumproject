package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.SessionUtil;
import cmu.curantis.dao.UserDAO;
import cmu.curantis.entities.UserBean;

@Path("/testuser")
public class testUserDAO {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public void test () {
		
		UserBean ub = new UserBean();
		ub.setCirclename("varunscircle");
		ub.setGeorelationship("Uncle");
		ub.setJoinStatus(true);
		ub.setPrimaryCaregiver(true);
		ub.setRelationshipNature("SON");
		ub.setTriggerEvent("illness");
		ub.setIdentity();
		ub.getIdentity().setCircleID(1);
		ub.getIdentity().setEmail("varunp@andrew.cmu.edu");
		UserDAO udao = new UserDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        //udao.create(session, ub);
        //udao.read(session, ub);
        //udao.update(session, ub);
        udao.delete(session, ub);
        tx.commit();
        session.close();
		
		/*CircleSubsBean bean = new CircleSubsBean();
		bean.setCardMemberFirstName("Ajinkya");
		bean.setCardMemberLastName("Nimbalkar");
		bean.setCircleName("mycircle");
		CircleSubsDAO dao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        dao.addCircleSubs(session, bean);        
        tx.commit();
        session.close();*/
		
		
		
		/*
		LoginOutput output = new LoginOutput();
		
		CaregiverInfoDAO caregiverdao = new CaregiverInfoDAO();
		Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        CaregiverInfoBean caregiver = caregiverdao.getCaregiverInfo(session, input.getEmail());
        if (caregiver != null && caregiver.getRegisteredStatus()) {
        	output.setMessage("Account already exist!");
        	output.setSuccess(false);
        } else {
        	caregiver = new CaregiverInfoBean();
        	caregiver.setEmail(input.getEmail());
        	caregiver.setFirstName(input.getFirstName());
        	caregiver.setMiddleName(input.getMiddleName());
        	caregiver.setLastName(input.getLastName());
        	caregiver.setPassword(input.getPassword());
        	caregiver.setAddress(input.getAddress());
        	caregiver.setPhoneNumber(input.getPhoneNo());
        	caregiver.setRegisteredStatus(true);
        	session.saveOrUpdate(caregiver);
        	output.setFirstName(input.getFirstName());
        	output.setLastName(input.getLastName());
        	output.setMessage("Register success!");
        	output.setSuccess(true);
        }
        tx.commit();
        session.close();
		
		return output;
		*/
	}
}
