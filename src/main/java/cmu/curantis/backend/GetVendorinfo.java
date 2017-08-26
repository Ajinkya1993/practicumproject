/**
 * 
 * This resource is used to get the vendor information.
 * 
 * Input fields: circleId, vendorname
 * Input Example: {
 *           "circleId":"1",
 *	"vendorname":"Exelon"
 * }
 * 
 * Output fields: contactperson, expenses, circleId, day, month, vendorName, notification,
 *  paymentsource, phoneno, remdays, vendorAccount, vendorAddr, vendortype, vendorWebsite
 * 
 * Output Example: 
 *  "bean": null,
 *   "list": [
 *       {
 *           "contactperson": "NA",
 *           "expenses": 215,
 *           "identity": {
 *               "circleId": 1,
 *               "day": 25,
 *               "month": 1,
 *               "vendorName": "Exelon"
 *           },
 *           "notification": null,
 *           "paymentsource": "Chase autopay",
 *           "phoneno": 8004833220,
 *           "remdays": 0,
 *           "vendorAccount": "11109672834",
 *           "vendorAddr": "P.O. Box 805398, Chicago, IL, 60680",
 *           "vendortype": "Electricity",
 *           "vendorWebsite": "www.excelon.com"
 *       }
 *   ],
 *   "listv": null,
 *   "map": null,
 *   "message": "Vendor viewed successfully",
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

@Path("/getvendorinformation")
public class GetVendorinfo {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public VendorOutput register(VendorInput input) {
		VendorOutput output = new VendorOutput();
		if(input.getCircleId() <= 0 || input.getVendorname() == null || input.getVendorname().length() == 0) {
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
				output.setMessage("Vendor does not exist!");
				output.setSuccess(false);
			} else {
				output.setMessage("Vendor viewed successfully");
				output.setList(lst);
				output.setSuccess(true);
			}
		tx.commit();
		session.close();
		return output;
	}
}
