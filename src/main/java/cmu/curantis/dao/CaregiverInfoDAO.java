package cmu.curantis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONException;

import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.entities.UserBean;

public class CaregiverInfoDAO {
	
	public boolean addCaregiverInfo(Session session, CaregiverInfoBean bean) throws JSONException{
		CaregiverInfoBean caregiver = new CaregiverInfoBean();
		String emailCheck = bean.getEmail();
		if(emailCheck == null) {
			return false;
		}
		Query query = session.createQuery("from caregiver_information where email = :email");
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
	
	 public CaregiverInfoBean getCaregiverInfo(Session session, CaregiverInfoBean bean){
		Query query = session.createQuery("from caregiver_information where email = :email");
		query.setString("email", bean.getEmail());
		List<CaregiverInfoBean> caregivers =  query.list();
	    if (caregivers == null || caregivers.size() == 0) {
	    	return null;
	    }
	    return caregivers.get(0);
    }
 
    public List<CaregiverInfoBean> getCaregiverInfoOfCircle(Session session,String circleId){
    	Query query = session.createQuery("from from caregiver_circle_info where circle_id = :circleId");
        query.setString("circle_id",circleId);
        List<CaregiverInfoBean> cgs = new ArrayList();
        List<UserBean> caregivers =  query.list();
        for(UserBean u: caregivers) {
        	String emailId = u.getIdentity().getEmail();
        	Query query2 = session.createQuery("from caregiver_information where email = :email");
            query2.setString("email",emailId);
            cgs.add((CaregiverInfoBean) query2.list().get(0));
        }
        session.close();
        return cgs;
    }
 
    public boolean deleteCaregiverInfo(Session session, CaregiverInfoBean bean) {
    	Query query = session.createQuery("from caregiver_information where email = :email");
		query.setString("email", bean.getEmail());
		List<CaregiverInfoBean> caregivers =  query.list();
		if (caregivers == null || caregivers.size() == 0) {
			return false;
		}
		session.delete(bean);		    
		return true;
    }
    
    public boolean updateEmployee(Session session, CaregiverInfoBean caregiver){
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