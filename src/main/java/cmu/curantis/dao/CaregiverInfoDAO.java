package cmu.curantis.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
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
        CaregiverInfoBean mybean = (CaregiverInfoBean)session.merge(bean);
        session.saveOrUpdate(mybean);
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
		CaregiverInfoBean dbbean = caregivers.get(0);
		if (bean.getFirstName() == null || bean.getFirstName().length()==0) {
			bean.setFirstName(dbbean.getFirstName());
		}
		if (bean.getLastName() == null || bean.getLastName().length()==0) {
			bean.setLastName(dbbean.getLastName());
		}
		if (bean.getMiddleName() == null || bean.getMiddleName().length()==0) {
			bean.setMiddleName(dbbean.getMiddleName());
		}
		if (bean.getAddress() == null || bean.getAddress().length() == 0) {
			bean.setAddress(dbbean.getAddress());
		}
		if (bean.getPhoneNumber() == null || bean.getPhoneNumber().length()==0) {
			bean.setPhoneNumber(dbbean.getPhoneNumber());
		}
		
		CaregiverInfoBean mybean = (CaregiverInfoBean)session.merge(bean);
		session.saveOrUpdate(mybean);	    
		return true; 
    }

}