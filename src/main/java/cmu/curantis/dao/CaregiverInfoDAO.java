package cmu.curantis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONException;
import org.json.JSONObject;

import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.entities.Employee;
import cmu.curantis.entities.CaregiverCircleBean;

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
 
    public List<CaregiverInfoBean> getCaregiverInfoOfCircle(Session session,Long circleId){
    	String q = "select * from caregiver_circle_info where circle_id = :circleId";
        Query query = session.createQuery(q);
        query.setLong("circle_id",circleId);
        List<CaregiverInfoBean> cgs = new ArrayList();
        List<CaregiverCircleBean> caregivers =  query.list();
        for(CaregiverCircleBean u: caregivers) {
        	String emailId = u.getIdentity().getEmail();
        	String q2 = "select * from caregiver_information where email = :email";
            Query query2 = session.createQuery(q2);
            query2.setString("email",emailId);
            cgs.add((CaregiverInfoBean) query2.list().get(0));
        }
        session.close();
        return cgs;
    }
 
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