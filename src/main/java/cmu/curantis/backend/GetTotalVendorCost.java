/**
 * This class is used to obtain the total cost expended for each vendor.
 * 
 * Input fields: email, circleId, emailToAdd
 * Input example:
 * {
 * "circleId": "1",
 * }
 * 
 * Output fields:  listv, monthlylist, message, success
 * Output Example:
 * {
 *   "listv": [
 *       "Exelon",
 *       "Peoples Gas",
 *       "City of Chicago",
 *       "The Admiral at the Lake",
 *       "North Shore Medical",
 *       "Benchmark Home Healthcare",
 *       "BlueCross Blue Shield of IL"
 *   ],
 *   "message": "Total Vendor Expenses viewed successfully",
 *   "monthlylist": [
 *       2580,
 *       2340,
 *       2880,
 *       89100,
 *       1200,
 *       3600,
 *       3240
 *   ],
 *   "success": true
 * }
 * 
 * @author curantisTeamCMU
 *
 */

package cmu.curantis.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.dao.VendorMgmtDAO;
import cmu.curantis.entities.VendorMgmtBean;
import cmu.curantis.inputbeans.VendorInput;
import cmu.curantis.outputbeans.VendorOutput;

@Path("/getvendortotalcost")
public class GetTotalVendorCost {
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
			
			Map<String, Double> mp= vendormgmtdao.getVendorCosts(session, vmbean);
			if (mp == null) {
				output.setMessage("No expenses exist!");
				output.setSuccess(false);
			} else {
				output.setMessage("Total Vendor Expenses viewed successfully");
				/*for(int i = 0; i < lst.size(); i++) {
					System.out.println("Component of list at "+i+ " is "+lst.get(i));
				}*/
				List<String>lstnm = new ArrayList<String>();
				List<Double> lstct = new ArrayList<Double>();
				
				Iterator it = mp.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        //String str = pair.getKey() + "space" + pair.getValue();
			        lstnm.add((String) pair.getKey());
			        lstct.add((Double) pair.getValue());
			        it.remove(); // avoids a ConcurrentModificationException
			    }
				output.setListv(lstnm);
				output.setMonthlylist(lstct);
				output.setSuccess(true);
			}
		tx.commit();
		session.close();
		return output;
	}
}
