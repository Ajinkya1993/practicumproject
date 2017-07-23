package cmu.curantis.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.CaregiverInfoBean;


public class CaregiverInfoDAO {
	
	public boolean addCaregiverInfo(Session session, CaregiverInfoBean bean) {
		Query query = session.createQuery("from CaregiverInfoBean where email = :email_id");
        query.setString("email_id",String.valueOf(bean.getEmail()));
        List<CaregiverInfoBean> caregivers =  query.list();
        if(caregivers.size() != 0) {
        	if(caregivers.size() != 1 || caregivers.get(0).getRegisteredStatus() == true) {
        		return false;
        	}
        }
        session.saveOrUpdate(bean);
        return true;
    }
	
	 public CaregiverInfoBean getCaregiverInfo(Session session, CaregiverInfoBean bean){
		Query query = session.createQuery("from CaregiverInfoBean where email = :email_id");
		query.setString("email_id", String.valueOf(bean.getEmail()));
		List<CaregiverInfoBean> caregivers =  query.list();
	    if (caregivers == null || caregivers.size() == 0) {
	    	return null;
	    }
	    return caregivers.get(0);
    }
 
    /*public List<CaregiverInfoBean> getCaregiverInfoOfCircle(Session session,String circleId){
    	Query query = session.createQuery("from UserBean where circle_id = :circleId");
        query.setString("circleId",circleId);
        List<CaregiverInfoBean> cgs = new ArrayList();
        List<UserBean> caregivers =  query.list();
        for(UserBean u: caregivers) {
        	String emailId = u.getIdentity().getEmail();
        	Query query2 = session.createQuery("from CaregiverInfoBean where email = :email_id");
            query2.setString("email_id",emailId);
            cgs.add((CaregiverInfoBean) query2.list().get(0));
        }
        session.close();
        return cgs;
    }*/
 
    public boolean deleteCaregiverInfo(Session session, CaregiverInfoBean bean) {
    	Query query = session.createQuery("from CaregiverInfoBean where email = :email_id");
		query.setString("email_id", String.valueOf(bean.getEmail()));
		List<CaregiverInfoBean> caregivers =  query.list();
		if (caregivers == null || caregivers.size() == 0) {
			return false;
		}
		CaregiverInfoBean mybean = (CaregiverInfoBean)session.merge(bean);
		session.delete(mybean);    
		return true;
    }
    
    public boolean updateCaregiverInfo(Session session, CaregiverInfoBean bean){
    	Query query = session.createQuery("from CaregiverInfoBean where email = :email_id");
		query.setString("email_id", String.valueOf(bean.getEmail()));
		List<CaregiverInfoBean> caregivers =  query.list();
		if (caregivers == null || caregivers.size() == 0) {
			return false;
		}
		CaregiverInfoBean mybean = (CaregiverInfoBean)session.merge(bean);
		session.saveOrUpdate(mybean);	    
		return true; 
    }

}