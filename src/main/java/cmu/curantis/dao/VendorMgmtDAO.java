package cmu.curantis.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import cmu.curantis.entities.VendorMgmtBean;

public class VendorMgmtDAO {
	public Boolean create(Session session, VendorMgmtBean ub) {
		long cicid = ub.getIdentity().getCircleId();
		String vendorname = ub.getIdentity().getVendorName();
		int mth = ub.getIdentity().getMonth();
		System.out.println("Circle id and vendor name and month  are "+cicid + " " + vendorname + " " + mth);
		if(cicid <= 0 || vendorname == null || vendorname.length() == 0 || mth <= 0 || mth > 12) {
			return false;
		}
		Query query = session.createQuery("FROM VendorMgmtBean WHERE vendorName = :vendorName AND circleId = :circleId AND month = :month");
        query.setString("vendorName",vendorname);
        query.setLong("circleId",cicid);
        query.setInteger("month",mth);
        List<VendorMgmtBean> lst =  query.list();
		
		if(lst.size() > 0) {
			return false;
		}
		
		VendorMgmtBean newub = new VendorMgmtBean();
		newub.setIdentity();
		newub.getIdentity().setCircleId(cicid);
		newub.getIdentity().setVendorName(vendorname);
		newub.getIdentity().setMonth(mth);
		newub.setExpenses(ub.getExpenses());
		newub.setVendorAccount(ub.getVendorAccount());
		newub.setVendorAddress(ub.getVendorAddress());
		newub.setVendorWebsite(ub.getVendorWebsite());
		session.save(newub);
		return true;
	}
	
	
	public Boolean update(Session session, VendorMgmtBean ub) {

		long cicid = ub.getIdentity().getCircleId();
		String vendorname = ub.getIdentity().getVendorName();
		System.out.println("Circle ID and vendor name are: "+ cicid + " " + vendorname);
		Query query = session.createQuery("from VendorMgmtBean where circleId = :circleId AND vendorName = :vendorName");
		query.setLong("circleId", cicid);
		query.setString("vendorName", vendorname);
		List<VendorMgmtBean> list = query.list();
		if(list == null || list.size() == 0) {
			return false;
		}
		for(int i = 0; i <list.size(); i++) {
		VendorMgmtBean crcb = list.get(i);
		System.out.println("Inside DAO method "+ ub.getVendorAccount() +  " " +  ub.getVendorAddress() + " " + ub.getVendorWebsite()+ " " + ub.getExpenses() + " " +  crcb.getIdentity().getMonth() + " " + ub.getIdentity().getCircleId());
		//updation of circle name is included here and not in another method.
		if(ub.getVendorAccount() == null || ub.getVendorAccount().length() == 0) {
			ub.setVendorAccount(crcb.getVendorAccount());
		}
		if(ub.getVendorAddress() == null || ub.getVendorAddress().length() == 0) {
			ub.setVendorAddress(crcb.getVendorAddress());
		}
		if(ub.getVendorWebsite()== null || ub.getVendorWebsite().length() == 0) {
			ub.setVendorWebsite(crcb.getVendorWebsite());
		}
		if(ub.getExpenses() <= 0) {
			ub.setVendorWebsite(crcb.getVendorWebsite());
		}
		//if(ub.getIdentity().getMonth() <= 0 || ub.getIdentity().getMonth() > 12) {
			ub.getIdentity().setMonth(crcb.getIdentity().getMonth());
		//}
		if(ub.getIdentity().getCircleId() <= 0) {
			ub.getIdentity().setCircleId(crcb.getIdentity().getCircleId());
		}
		if(ub.getIdentity().getVendorName() == null || ub.getIdentity().getVendorName().length() == 0) {
			ub.getIdentity().setVendorName(crcb.getIdentity().getVendorName());
		}
		VendorMgmtBean mybean = (VendorMgmtBean)session.merge(ub);
		session.update(mybean);
		}
		return true;
	}
	
	
	public Boolean delete(Session session, VendorMgmtBean ub) {
		long cicid = ub.getIdentity().getCircleId();
		String vendorname = ub.getIdentity().getVendorName();
		Query query = session.createQuery("from VendorMgmtBean where circleId = :circleId AND vendorName = :vendorName");
		query.setLong("circleId", cicid);
		query.setString("vendorName", vendorname);
		List<VendorMgmtBean> list =  query.list();
		if (list == null || list.size() == 0) {
			return false;
		}
		for(int i = 0; i < list.size(); i++) {
		VendorMgmtBean oribean = list.get(i);
		VendorMgmtBean mybean = (VendorMgmtBean)session.merge(oribean);
		session.delete(mybean);	
		}
		return true;
	}
	
	public List<VendorMgmtBean> getVendorInfo(Session session, VendorMgmtBean ub) {
		long cicid = ub.getIdentity().getCircleId();
		String vendorname = ub.getIdentity().getVendorName();
		Query query = session.createQuery("from VendorMgmtBean where circleId = :circleId AND vendorName = :vendorName");
		query.setLong("circleId", cicid);
		query.setString("vendorName", vendorname);
        List<VendorMgmtBean> list = query.list();
        if(list == null || list.size() == 0) {
            return null;
        }
        return list;
    }
	
	public List<VendorMgmtBean> getVendors(Session session, VendorMgmtBean ub) {
		long cicid = ub.getIdentity().getCircleId();
		Query query = session.createQuery("from VendorMgmtBean where circleId = :circleId");
		query.setLong("circleId", cicid);
        List<VendorMgmtBean> list = query.list();
        if(list == null || list.size() == 0) {
            return null;
        }
        return list;
    }
	
	public List<VendorMgmtBean> getMonthyExpenses(Session session, VendorMgmtBean ub) {
		long cicid = ub.getIdentity().getCircleId();
		String vendorname = ub.getIdentity().getVendorName();
		int month = ub.getIdentity().getMonth();
		Query query = session.createQuery("from VendorMgmtBean where circleId = :circleId AND vendorName = :vendorName AND month = :month");
		query.setLong("circleId", cicid);
		query.setString("vendorName", vendorname);
		query.setLong("month", month);
        List<VendorMgmtBean> list = query.list();
        if(list == null || list.size() == 0) {
            return null;
        }
        return list;
    }
}