package cmu.curantis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import cmu.curantis.entities.CaregiverCircleBean;

public class CaregiverCircleDAO {
	public Boolean create(Session session, CaregiverCircleBean ub) {
		String emailid = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		if(emailid == null || cicid <= 0) {
			return false;
		}
		Query query = session.createQuery("FROM CaregiverCircleBean WHERE email = :email AND circle_id = :circle_id");
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
	
	public List<CaregiverCircleBean> getByEmail(Session session, String email) {
        Query query = session.createQuery("FROM CaregiverCircleBean WHERE email = :email");
        query.setString("email", email);
        List<CaregiverCircleBean> list = query.list();
        if(list == null || list.size() == 0) {
            return null;
        }
        return list;
    }
    
    public CaregiverCircleBean getByEmailAndId(Session session, String email, long cicid) {
        Query query = session.createQuery("FROM CaregiverCircleBean WHERE email = :email AND circle_id = :circle_id");
        query.setString("email", email);
        query.setLong("circle_id", cicid);
        List<CaregiverCircleBean> list = query.list();
        if(list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
    
    public List<CaregiverCircleBean> getByCircleId(Session session, long cicid) {
        Query query = session.createQuery("FROM CaregiverCircleBean WHERE circle_id = :circle_id");
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
		Query query = session.createQuery("FROM CaregiverCircleBean WHERE email = :em AND circle_id = :cicid");
		query.setString("em", em);
		query.setLong("cicid", cicid); 
		List<CaregiverCircleBean> list = query.list();
		if(list == null || list.size() == 0) {
			return false;
		}
		CaregiverCircleBean crcb = list.get(0);
		//updation of circle name is included here and not in another method.
		if(ub.getCirclename() == null || ub.getCirclename().length() == 0) {
			ub.setCirclename(crcb.getCirclename());
		}
		if(ub.getGeorelationship() == null || ub.getGeorelationship().length() == 0) {
			ub.setGeorelationship(crcb.getGeorelationship());
		}
		if(ub.getRelationshipNature() == null || ub.getRelationshipNature().length() == 0) {
			ub.setRelationshipNature(crcb.getGeorelationship());
		}
		if(ub.getTriggerEvent() == null || ub.getTriggerEvent().length() == 0) {
			ub.setTriggerEvent(crcb.getTriggerEvent());
		}
		CaregiverCircleBean mybean = (CaregiverCircleBean)session.merge(ub);
		session.update(mybean);
		return true;
	}
	
	public Boolean delete(Session session, CaregiverCircleBean ub) {
		String em = ub.getIdentity().getEmail();
		long cicid = ub.getIdentity().getCircleID();
		Query query = session.createQuery("from CaregiverCircleBean where circle_id = :circleid AND email = :email");
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
