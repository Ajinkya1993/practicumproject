package cmu.curantis.backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.CaregiverCircleDAO;
import cmu.curantis.dao.CaregiverInfoDAO;
import cmu.curantis.dao.CircleSubsDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.dao.VendorMgmtDAO;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.entities.CircleSubsBean;
import cmu.curantis.entities.VendorMgmtBean;
import cmu.curantis.inputbeans.PaymentInput;
import cmu.curantis.inputbeans.RegisterInput;
import cmu.curantis.inputbeans.VendorInput;
import cmu.curantis.outputbeans.LoginOutput;
import cmu.curantis.outputbeans.VendorOutput;

@Path("/getvendorsofcircle")
public class GetVendors {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public VendorOutput register(VendorInput input) {
		VendorOutput output = new VendorOutput();
		if(input.getCircleId() <= 0) {
			output.setSuccess(false);
			output.setMessage("Missing parameters!");
	    	return output;
		}
		VendorMgmtDAO vendormgmtdao = new VendorMgmtDAO();
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		
			VendorMgmtBean vmbean = new VendorMgmtBean();
			
			vmbean.setIdentity();
			vmbean.getIdentity().setCircleId(input.getCircleId());
			List<VendorMgmtBean> lst = vendormgmtdao.getVendors(session, vmbean);
			if(lst== null) {
				output.setMessage("Vendor does not exist!");
				output.setSuccess(false);
				return output;
			}
			Set<String> set = new HashSet<String>();
			//may need to check for lower/upper case
			
			//get system date and time
			Calendar cal = Calendar.getInstance();
		    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		    int month = cal.get(Calendar.MONTH);
		    String dayOfMonthStr = String.valueOf(dayOfMonth);
		    String monthstr = String.valueOf(month); //as month in java calendar starts from 0
		    int month_current = Integer.parseInt(monthstr) + 1;
		    int day_current = Integer.parseInt(dayOfMonthStr);
		    Calendar calendar1 = Calendar.getInstance();
		    calendar1.set(2017, month_current, day_current);
		    long miliSecondForDate1 = calendar1.getTimeInMillis();
		    
			//to get monthly cost
			Map<String, List<Double>> mp = new HashMap<String, List<Double>>();
			for(VendorMgmtBean vb: lst) {
				
				Calendar calendar2 = Calendar.getInstance();
				calendar2.set(2017, vb.getIdentity().getMonth(), vb.getIdentity().getDay());
				long miliSecondForDate2 = calendar2.getTimeInMillis();
				long diffInMilis = miliSecondForDate2 - miliSecondForDate1;
				long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);
				
				
				set.add(vb.getIdentity().getVendorName());
				//assuming monthly cost is the same throughout
				if(!mp.containsKey(vb.getIdentity().getVendorName())) {
					List<Double> list = new ArrayList<Double>();
					list.add(vb.getExpenses());
					list.add((double) vb.getIdentity().getMonth());
					//0 denotes false 1 denotes true, so if 1 then it means they need notification
					if(0 < diffInDays && diffInDays <= 7) {
					list.add(1.0);
					if(diffInDays > 0) {
					list.add((double)diffInDays);
					} else {
						list.add(0.0);	
					}
					} else {
					list.add(0.0);	
					if(diffInDays > 0) {
					list.add((double)diffInDays);
					}else {
						list.add(0.0);	
					}
					}
					mp.put(vb.getIdentity().getVendorName(), list);
				}else {
					List<Double> lstnw = mp.get(vb.getIdentity().getVendorName());
					
					//lst.get(2) is a boolean to push notification or not...if not, then latest no of rem days should be calculated
					if(lstnw.get(2) == 0) {
						if(0 < diffInDays && diffInDays <= 7){
							lstnw.set(1, (double) vb.getIdentity().getMonth());
							lstnw.set(2, 1.0);
							lstnw.set(3, (double)diffInDays);
							mp.put(vb.getIdentity().getVendorName(), lstnw);
						} else {
							if(0 <diffInDays && diffInDays < lstnw.get(3)) { 
								lstnw.set(1, (double) vb.getIdentity().getMonth());
								lstnw.set(3, (double)diffInDays);
							}
						}
					}
					
				}
			}
			if (set == null || set.size() == 0) {
				output.setMessage("Vendor does not exist!");
				output.setSuccess(false);
			} else {
				output.setMessage("Vendor viewed successfully");
				output.setSet(set);
				output.setSuccess(true);
				output.setMap(mp);
			}
		tx.commit();
		session.close();
		return output;
	}
}
