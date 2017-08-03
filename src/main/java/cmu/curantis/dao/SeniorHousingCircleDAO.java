package cmu.curantis.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;

import cmu.curantis.entities.SeniorHousingCircleBean;

public class SeniorHousingCircleDAO {
	
	public void addPreference(Session session, SeniorHousingCircleBean bean) {
		SeniorHousingCircleBean mybean = (SeniorHousingCircleBean)session.merge(bean);
        session.saveOrUpdate(mybean);
	}
	
	public SeniorHousingCircleBean getPreference(Session session, long circleId) {
		Query query = session.createQuery("from SeniorHousingCircleBean where circle_id = :circleid");
		query.setString("circleid", String.valueOf(circleId));
	    List<SeniorHousingCircleBean> preferences =  query.list();
	    if (preferences == null || preferences.size() == 0) {
	    	return null;
	    }
	    return preferences.get(0);
	}
	
	public boolean updatePreference(Session session, SeniorHousingCircleBean bean) {
		Query query = session.createQuery("from SeniorHousingCircleBean where circle_id = :circleid");
		query.setString("circleid", String.valueOf(bean.getCircleId()));
	    List<SeniorHousingCircleBean> original =  query.list();
	    if (original == null || original.size() == 0) {
	    	return false;
	    }
	    
	    SeniorHousingCircleBean mybean = original.get(0);
	    try {
			JSONObject oriPrf = new JSONObject(mybean.getPreference());
			JSONObject newPrf = new JSONObject(bean.getPreference());
			for (String key : JSONObject.getNames(newPrf)) {
				oriPrf.put(key, newPrf.get(key));
			}
			mybean.setPreference(oriPrf.toString());
			mybean = (SeniorHousingCircleBean) session.merge(mybean);
			session.saveOrUpdate(mybean);
			return true;
		} catch (JSONException e) {
			return false;
		}
	}
}
