package cmu.curantis.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.mapping.List;

import cmu.curantis.entities.UserBean;

public class UserDAO {
	private Boolean create(Session session, UserBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("SELECT * FROM caregiver_circle_info WHERE email = :em AND circle_id = :cicid");
		query.setParameter("em", em);
		query.setParameter("cicid", cicid);
		List list = (List) query.list();
		if(((java.util.List) list).size() > 0) {
			return false;
		}
		
		UserBean newub = new UserBean();
		newub.getIdentity().setCircleID(cicid);
		newub.getIdentity().setEmail(em);
		newub.setCirclename(ub.getCirclename());
		newub.setGeorelationship(ub.getGeorelationship());
		newub.setJoinStatus(ub.getJoinStatus());
		newub.setPrimaryCaregiver(ub.getPrimaryCaregiver());
		newub.setRelationshipNature(ub.getRelationshipNature());
		newub.setTriggerEvent(ub.getTriggerEvent());
		session.save(newub);
		return true;
		
	}
	
	private List read(Session session, UserBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("SELECT * FROM caregiver_circle_info WHERE email = :em AND circle_id = :cicid");
		query.setParameter("em", em);
		query.setParameter("cicid", cicid);
		List list = (List) query.list();
		if(((java.util.List) list).size() == 0 || list == null) {
			return null;
		}
		return list;
	}
	
	private Boolean update(Session session, UserBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("SELECT * FROM caregiver_circle_info WHERE email = :em AND circle_id = :cicid");
		query.setParameter("em", em);
		query.setParameter("cicid", cicid);
		List list = (List) query.list();
		if(((java.util.List) list).size() == 0 || list == null) {
			return false;
		}
		session.saveOrUpdate(ub);
		return true;
	}
	
	private Boolean delete(Session session, UserBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("SELECT * FROM caregiver_circle_info WHERE email = :em AND circle_id = :cicid");
		query.setParameter("em", em);
		query.setParameter("cicid", cicid);
		List list = (List) query.list();
		if(((java.util.List) list).size() == 0 || list == null) {
			return false;
		}
		Query querydel = session.createQuery("DELETE FROM caregiver_circle_info WHERE email = :em AND circle_id = :cicid");
        querydel.setParameter("em",em);
        querydel.setParameter("cicid",cicid);
        querydel.executeUpdate();
        session.close();
		return true;
	}
}
