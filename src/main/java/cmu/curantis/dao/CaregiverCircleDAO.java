package cmu.curantis.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cmu.curantis.entities.CaregiverCircleBean;

public class CaregiverCircleDAO {
	public Boolean create(Session session, CaregiverCircleBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("SELECT * FROM caregiver_circle_info WHERE email = :em AND circle_id = :cicid");
		query.setParameter("em", em);
		query.setParameter("cicid", cicid);
		List list = (List) query.list();
		if(((java.util.List) list).size() > 0) {
			return false;
		}
		
		CaregiverCircleBean newub = new CaregiverCircleBean();
		newub.getIdentity().setCircleID(cicid);
		newub.getIdentity().setEmail(em);
		newub.setCirclename(ub.getCirclename());
		newub.setGeorelationship(ub.getGeorelationship());
		newub.setJoinStatus(ub.getJoinStatus());
		newub.setPrimaryCaregiver(ub.getPrimaryCaregiver());  //Check if the primary caregiver exists?
		newub.setRelationshipNature(ub.getRelationshipNature());
		newub.setTriggerEvent(ub.getTriggerEvent());
		session.save(newub);
		return true;
		
	}
	
	public List read(Session session, CaregiverCircleBean ub) {  //Should be (Session session, String email) instead of CaregiverCircleBean ub
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
	public List<CaregiverCircleBean> getCircleByEmail(Session session, String email) {
	    String q = "select * from caregiver_circle_info where email = :email";
	    Query query = session.createQuery(q);
	    query.setString("email", email);
	    List<CaregiverCircleBean> circleList = query.list();
	    return circleList;
	}
	public List<CaregiverCircleBean> getCircleByCircleId(Session session, long circleId) {
	    String q = "select * from caregiver_circle_info where circle_id = :circleId";
        Query query = session.createQuery(q);
        query.setLong("circleId", circleId);
        List<CaregiverCircleBean> circleList = query.list();
        return circleList;
	}
	
	public Boolean update(Session session, CaregiverCircleBean ub) {
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
	
	public Boolean delete(Session session, CaregiverCircleBean ub) {
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
