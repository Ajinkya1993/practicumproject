/**
 * This class is used to retrieve a given vendors expenditure.
 * 
 * Input fields: circleId, vendorname
 * Input example:
 * {
 * "circleId": "1",
 * "vendorname":"City of Chicago"
 * }
 * 
 * Output fields:  list, contactperson, expenses,circleId,day,month,vendorName,notification,
 *  paymentsource, phoneno, remdays,  vendorAccount, vendorAddr, vendortype, vendorWebsite
 * Output Example: 
 * {
 *   "bean": null,
 *   "list": [
 *       {
 *           "contactperson": "NA",
 *           "expenses": 240,
 *           "identity": {
 *               "circleId": 1,
 *               "day": 23,
 *               "month": 1,
 *               "vendorName": "City of Chicago"
 *           },
 *           "notification": null,
 *           "paymentsource": "Chase autopay",
 *           "phoneno": 3127445000,
 *           "remdays": 0,
 *           "vendorAccount": "7621973629",
 *           "vendorAddr": "121 City Hall, Chicago, IL 60602",
 *           "vendortype": "Water",
 *           "vendorWebsite": "www.cityofchicago.com"
 *       }
 *   ],
 *   "listv": null,
 *   "map": null,
 *   "message": "Vendor Cost viewed successfully",
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

import cmu.curantis.dao.SessionUtil;
import cmu.curantis.dao.VendorMgmtDAO;
import cmu.curantis.entities.VendorMgmtBean;
import cmu.curantis.inputbeans.VendorInput;
import cmu.curantis.outputbeans.VendorOutput;

@Path("/getexpensesvendor")
public class GetVendorExpenses {
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public VendorOutput register(VendorInput input) {
		VendorOutput output = new VendorOutput();
		if(input.getCircleId() <= 0  || input.getVendorname() == null || input.getVendorname().length() == 0) {
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
			vmbean.getIdentity().setVendorName(input.getVendorname());
			List<VendorMgmtBean> lst = vendormgmtdao.getVendorInfo(session, vmbean);
			if (lst == null) {
				output.setMessage("Cost for this vendor does not exist!");
				output.setSuccess(false);
			} else {
				output.setMessage("Vendor Cost viewed successfully");
				output.setList(lst);
				output.setSuccess(true);
			}
		tx.commit();
		session.close();
		return output;
	}
}
