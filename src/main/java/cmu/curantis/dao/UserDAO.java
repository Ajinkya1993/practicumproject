package cmu.curantis.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.entities.UserBean;

public class UserDAO {
	public Boolean create(Session session, UserBean ub) {
		UserBean userbee = new UserBean();
		String emailid = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		if(emailid == null || cicid < 0) {
			return false;
		}
		Query query = session.createQuery("FROM UserBean WHERE email = :email AND circle_id = :circle_id");
        query.setString("email",emailid);
        query.setLong("circle_id",cicid);
        List<UserBean> lst =  query.list();
		
		if(lst.size() > 0) {
			return false;
		}
		
		UserBean newub = new UserBean();
		newub.setIdentity();
		newub.getIdentity().setCircleID(cicid);
		newub.getIdentity().setEmail(emailid);
		newub.setCirclename(ub.getCirclename());
		newub.setGeorelationship(ub.getGeorelationship());
		newub.setJoinStatus(ub.getJoinStatus());
		newub.setPrimaryCaregiver(ub.getPrimaryCaregiver());
		newub.setRelationshipNature(ub.getRelationshipNature());
		newub.setTriggerEvent(ub.getTriggerEvent());
		session.save(newub);
		return true;
		
	}
	
	public List read(Session session, UserBean ub) {
		String email = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("FROM UserBean WHERE email = :email AND circle_id = :circle_id");
		query.setString("email", email);
		query.setLong("circle_id", cicid);
		List<UserBean> list = query.list();
		if(list == null || list.size() == 0) {
			return null;
		}
		return list;
	}
	
	public Boolean update(Session session, UserBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("FROM UserDAO WHERE email = :em AND circle_id = :cicid");
		query.setParameter("em", em);
		query.setParameter("cicid", cicid);
		List<UserBean> list = query.list();
		if(list == null || list.size() == 0) {
			return false;
		}
		session.saveOrUpdate(ub);
		return true;
	}
	
	public Boolean delete(Session session, UserBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("FROM UserBean WHERE email = :em AND circle_id = :cicid");
		query.setParameter("em", em);
		query.setParameter("cicid", cicid);
		List<UserBean> list = query.list();
		if(list == null || list.size() == 0) {
			return false;
		}
		Query querydel = session.createQuery("DELETE UserBean WHERE email = :em AND circle_id = :cicid");
        querydel.setParameter("em",em);
        querydel.setParameter("cicid",cicid);
        querydel.executeUpdate();
        session.close();
		return true;
	}
}
