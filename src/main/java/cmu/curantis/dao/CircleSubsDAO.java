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
	public int addCircleSubs(Session session, CircleSubsBean bean){
		session.save(bean);
		List<CircleSubsBean> result = session.createQuery("from circle_subscription ORDER BY circle_id DESC").setMaxResults(1).list();
		if (result == null || result.size() == 0) {
			return -1;
		}
		return result.get(0).getCircleId();        
    }
	
	 public CircleSubsBean getCircleSubs(Session session, CircleSubsBean bean){
        Query query = session.createQuery("from circle_subscription where circle_id = :circleid");
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
		Query query = session.createQuery("from circle_subscription where circle_id = :circleid");
		query.setString("circleid", String.valueOf(bean.getCircleId()));
		List<CircleSubsBean> circles =  query.list();
		if (circles == null || circles.size() == 0) {
			return false;
		}
		session.saveOrUpdate(bean);		    
		return true;
	}
	
	/*
	 * Returns true on deletion. Returns false when row to be deleted is not found.
	 */
	public boolean deleteCircleSubs (Session session, CircleSubsBean bean) {
		Query query = session.createQuery("from circle_subscription where circle_id = :circleid");
		query.setString("circleid", String.valueOf(bean.getCircleId()));
		List<CircleSubsBean> circles =  query.list();
		if (circles == null || circles.size() == 0) {
			return false;
		}
		session.delete(bean);		    
		return true;
	}
	 
	 
	
}
