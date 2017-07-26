package cmu.curantis.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.entities.CircleSubsBean;
import cmu.curantis.entities.Employee;

import java.util.List;

import org.hibernate.Query;

public class CircleSubsDAO {
	
	/*
	 * Returns the new circle id for this circle. Returns -1 on error.
	 */
	public long addCircleSubs(Session session, String name){
		CircleSubsBean bean = new CircleSubsBean();
		bean.setCircleName(name);
		session.save(bean);
		List<CircleSubsBean> result = session.createQuery("from CircleSubsBean ORDER BY circle_id DESC").setMaxResults(1).list();
		if (result == null || result.size() == 0) {
			return -1;
		}
		return result.get(0).getCircleId();        
    }
	
	 public CircleSubsBean getCircleSubs(Session session, CircleSubsBean bean){
        Query query = session.createQuery("from CircleSubsBean where circle_id = :circleid");
	    query.setString("circleid", String.valueOf(bean.getCircleId()));
	    List<CircleSubsBean> circles =  query.list();
	    if (circles == null || circles.size() == 0) {
	    	return null;
	    }
	    return circles.get(0);
	 }
	 
	 /*
	  * Updates row only if it exists. Returns false when row does not exist.
	  */
	public boolean updateCircleSubs(Session session, CircleSubsBean bean){
		Query query = session.createQuery("from CircleSubsBean where circle_id = :circleid");
		query.setString("circleid", String.valueOf(bean.getCircleId()));
		List<CircleSubsBean> circles =  query.list();
		if (circles == null || circles.size() == 0) {
			return false;
		}
		CircleSubsBean oribean = circles.get(0);
		if (bean.getCircleName() == null || bean.getCircleName().length() == 0) {
			bean.setCircleName(oribean.getCircleName());
		}
		if (bean.getLovedoneAddress() == null || bean.getLovedoneAddress().length()==0) {
			bean.setLovedoneAddress(oribean.getLovedoneAddress());
		}
		if (bean.getServicesSubscribed() == null || bean.getServicesSubscribed().length()==0) {
			bean.setServicesSubscribed(oribean.getServicesSubscribed());
		}
		
		CircleSubsBean mybean = (CircleSubsBean)session.merge(bean);
		session.saveOrUpdate(mybean);		    
		return true;
	}
	
	public boolean updateCircleName(Session session, CircleSubsBean bean){
		Query query = session.createQuery("from CircleSubsBean where circle_id = :circleid");
		query.setString("circleid", String.valueOf(bean.getCircleId()));
		List<CircleSubsBean> circles =  query.list();
		if (circles == null || circles.size() == 0) {
			return false;
		}
		CircleSubsBean oribean = circles.get(0);
		oribean.setCircleName(bean.getCircleName());
		
		CircleSubsBean mybean = (CircleSubsBean)session.merge(oribean);
		session.saveOrUpdate(mybean);		    
		return true;
	}
	
	/*
	 * Returns true on deletion. Returns false when row to be deleted is not found.
	 */
	public boolean deleteCircleSubs (Session session, CircleSubsBean bean) {
		Query query = session.createQuery("from CircleSubsBean where circle_id = :circleid");
		query.setString("circleid", String.valueOf(bean.getCircleId()));
		List<CircleSubsBean> circles =  query.list();
		if (circles == null || circles.size() == 0) {
			return false;
		}
		CircleSubsBean mybean = (CircleSubsBean)session.merge(bean);
		
		session.delete(mybean);		    
		return true;
	}
	 
	 
	
}
