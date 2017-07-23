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
import cmu.curantis.entities.UserBean;

public class CaregiverInfoDAO {
	
	public boolean addCaregiverInfo(Session session, CaregiverInfoBean bean) throws JSONException{
		CaregiverInfoBean caregiver = new CaregiverInfoBean();
		String emailCheck = bean.getEmail();
		if(emailCheck == null) {
			return false;
		}
		String q = "select * from caregiver_information where email = :email";
        Query query = session.createQuery(q);
        query.setString("email",emailCheck);
        List<CaregiverInfoBean> caregivers =  query.list();
        if(caregivers != null) {
        	if(caregivers.size() != 1 || caregivers.get(0).getRegisteredStatus() == true) {
        		return false;
        	}
        }
        caregiver.setEmail(bean.getEmail());
        caregiver.setFirstName(bean.getFirstName());
        caregiver.setMiddleName(bean.getMiddleName());
        caregiver.setLastName(bean.getLastName());
        caregiver.setPassword(bean.getPassword());
        caregiver.setAddress(bean.getAddress());
        caregiver.setPhoneNumber(bean.getPhoneNumber());
        caregiver.setRegisteredStatus(bean.getRegisteredStatus());
        session.save(caregiver);
        return true;
    }
	
	 public CaregiverInfoBean getCaregiverInfo(Session session, String email){
    	String q2 = "from CaregiverInfoBean where email = :email";
        Query query2 = session.createQuery(q2);
        query2.setString("email",email);
        if(query2.list().size() == 0) {
        	return null;
        }
        return (CaregiverInfoBean) query2.list().get(0);
    }
 
    public List<CaregiverInfoBean> getCaregiverInfoOfCircle(Session session,Long circleId){
    	String q = "select * from caregiver_circle_info where circle_id = :circleId";
        Query query = session.createQuery(q);
        query.setLong("circle_id",circleId);
        List<CaregiverInfoBean> cgs = new ArrayList();
        List<UserBean> caregivers =  query.list();
        for(UserBean u: caregivers) {
        	String emailId = u.getIdentity().getEmail();
        	String q2 = "select * from caregiver_information where email = :email";
            Query query2 = session.createQuery(q2);
            query2.setString("email",emailId);
            cgs.add((CaregiverInfoBean) query2.list().get(0));
        }
        session.close();
        return cgs;
    }
 
    public boolean deleteCaregiverInfo(Session session, String email) {
		String q = "select * from caregiver_information where email = :email";
        Query query = session.createQuery(q);
        query.setString("email",email);
        List<CaregiverInfoBean> caregivers =  query.list();
        if(caregivers == null) {
        	return false;
        }
        String hql = "delete from caregiver_information where email = :email";
        Query query2 = session.createQuery(hql);
        query2.setString("email",email);
        query2.executeUpdate();
        session.close();
        return true;
    }
    
    public boolean updateCaregiverInfo(Session session, CaregiverInfoBean caregiver){
    	Query query = session.createQuery("from caregiver_information where email = :email");
		query.setString("email", String.valueOf(caregiver.getEmail()));
		List<CaregiverInfoBean> caregivers =  query.list();
		if (caregivers == null || caregivers.size() == 0) {
			return false;
		}
		session.saveOrUpdate(caregiver);		    
		return true;
    }

}