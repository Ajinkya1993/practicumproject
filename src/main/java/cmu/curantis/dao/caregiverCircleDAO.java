package cmu.curantis.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.entities.CircleSubsBean;
import cmu.curantis.entities.CaregiverCircleBean;

public class caregiverCircleDAO {
	public Boolean create(Session session, CaregiverCircleBean ub) {
		String emailid = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		if(emailid == null || cicid < 0) {
			return false;
		}
		Query query = session.createQuery("FROM UserBean WHERE email = :email AND circle_id = :circle_id");
        query.setString("email",emailid);
        query.setLong("circle_id",cicid);
        List<CaregiverCircleBean> lst =  query.list();
		
		if(lst.size() > 0) {
			return false;
		}
		
		CaregiverCircleBean newub = new CaregiverCircleBean();
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
	
	public List read(Session session, CaregiverCircleBean ub) {
		String email = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("FROM UserBean WHERE email = :email AND circle_id = :circle_id");
		query.setString("email", email);
		query.setLong("circle_id", cicid);
		List<CaregiverCircleBean> list = query.list();
		if(list == null || list.size() == 0) {
			return null;
		}
		return list;
	}
	
	public List readcircle(Session session, CaregiverCircleBean ub) {
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("FROM UserBean WHERE circle_id = :circle_id");
		query.setLong("circle_id", cicid);
		List<CaregiverCircleBean> list = query.list();
		if(list == null || list.size() == 0) {
			return null;
		}
		return list;
	}
	
	public Boolean update(Session session, CaregiverCircleBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("FROM UserBean WHERE email = :em AND circle_id = :cicid");
		query.setString("em", em);
		query.setLong("cicid", cicid); 
		List<CaregiverCircleBean> list = query.list();
		if(list == null || list.size() == 0) {
			return false;
		}
		CaregiverCircleBean mybean = (CaregiverCircleBean)session.merge(ub);
		session.saveOrUpdate(mybean);
		return true;
	}
	
	public Boolean delete(Session session, CaregiverCircleBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("from UserBean where circle_id = :circleid AND email = :email");
		query.setLong("circleid", cicid);
		query.setString("email", em);
		List<CaregiverCircleBean> list =  query.list();
		if (list == null || list.size() == 0) {
			return false;
		}
		CaregiverCircleBean mybean = (CaregiverCircleBean)session.merge(ub);
		
		session.delete(mybean);		    
		return true;
	}
}
