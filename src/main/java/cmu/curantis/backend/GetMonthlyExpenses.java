/**
 * This class is used to retrieve a given months expenditure for a given careteam.
 * 
 * Input fields: circleId, month
 * Input example:
 * {
 * "circleId":1,
 * "month":7
 * }
 * 
 * Output fields: contactperson, expenses, circleId, day, month, vendorName,
 *  paymentsource, phoneno, remdays, vendorAccount, vendorAddr, vendortype, vendorWebsite
 * Output Example:
 * {
 *   "bean": null,
 *   "list": [
 *       {
 *           "contactperson": "Maureen Slivkin",
 *           "expenses": 8100,
 *           "identity": {
 *               "circleId": 1,
 *               "day": 1,
 *               "month": 7,
 *               "vendorName": "The Admiral at the Lake"
 *           },
 *           "notification": null,
 *           "paymentsource": "Schwab Checking",
 *           "phoneno": 7734331800,
 *           "remdays": 0,
 *           "vendorAccount": "37821",
 *           "vendorAddr": "929 Foster Avenue, Chicago, IL 60640 ",
 *           "vendortype": "Senior Housing",
 *           "vendorWebsite": "www.admiral.kendal.org"
 *       }
 *   ],
 *   "listv": null,
 *   "map": null,
 *   "message": "Cost viewed successfully",
 *   "monthlylist": null,
 *   "set": null,
 *   "success": true
 * }
 * 
 * @author curantisTeamCMU
 *
 */

package cmu.curantis.backend;

import java.util.List;

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
import cmu.curantis.outputbeans.PaymentOutput;
import cmu.curantis.outputbeans.VendorOutput;

@Path("/getmonthlyexpenses")
public class GetMonthlyExpenses {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public VendorOutput register(VendorInput input) {
		VendorOutput output = new VendorOutput();
		if(input.getCircleId() <= 0  || input.getMonth() <= 0 || input.getMonth() > 12) {
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
			vmbean.getIdentity().setMonth(input.getMonth());
			List<VendorMgmtBean> lst = vendormgmtdao.getMonthyExpenses(session, vmbean);
			if (lst == null) {
				output.setMessage("Cost does not exist!");
				output.setSuccess(false);
			} else {
				output.setMessage("Cost viewed successfully");
				output.setList(lst);
				output.setSuccess(true);
			}
		tx.commit();
		session.close();
		return output;
	}
}
