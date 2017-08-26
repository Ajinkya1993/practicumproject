/**
 * This resource allows to update vendors information.

 *  Input Fields: circleId, circleName, vendorname, vendorwebsite, vendoraddress, vendoraddress, vendoraccountnumber, month, day, replicationfactor, expenses, vendortype, contactperson, phoneno, paymentsource
 *  Input Example: {   "circleId":1 ,
 * "email":"john@gmail.com",
 *   "circleName": "John Banta",
 *	"vendorname":"Duquesnue Light Vendor",
 *	"vendorwebsite":"http://www.google.com",
 *	"vendoraddress":"Duquesnue Light, Chicago, IL, 600120",
 *	"vendoraccountnumber":"52161",
 *	"month":"7",
 *	"day":"24",
 *	"replicationfactor":"3",
 *	"expenses" :"1200",
 *	"vendortype":"Housing Service",
 *	"contactperson":"Enrique Hunt",
 *	"phoneno":"84236182931",
 *  "paymentsource":"Schwab Checking"
 *  }
 * Output Fields: message, success
 * Output Example: {
 *   "message": "Updated added successfully",
 *   "success": true
 * }

 * @author curantisTeamCMU
 */

package cmu.curantis.backend;

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

@Path("/updatevendor")
public class UpdateVendor {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public VendorOutput register(VendorInput input) {
		VendorOutput output = new VendorOutput();
		if(input.getCircleId() <= 0) {
			output.setMessage("Missing circleId!");
	        output.setSuccess(false);
	        return output;
		}
		if(input.getVendorname() == null || input.getVendorname().length() == 0) {
			output.setMessage("Missing Vendor Name!");
	        output.setSuccess(false);
	        return output;
		}
		
		VendorMgmtDAO vendormgmtdao = new VendorMgmtDAO();
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		//not required to check if user belongs to that circle as it would be done before

			VendorMgmtBean vmbean = new VendorMgmtBean();
			vmbean.setIdentity();
			vmbean.getIdentity().setCircleId(input.getCircleId());
			vmbean.getIdentity().setMonth(input.getMonth());
			vmbean.getIdentity().setDay(input.getDay());
			vmbean.getIdentity().setVendorName(input.getVendorname());
			vmbean.setExpenses(input.getExpenses());
			vmbean.setVendorAccount(input.getVendoraccountnumber());
			vmbean.setVendorAddr(input.getVendoraddress());
			vmbean.setVendorWebsite(input.getVendorwebsite());
			vmbean.setContactperson(input.getContactperson());
			vmbean.setPaymentsource(input.getPaymentsource());
			vmbean.setVendortype(input.getVendortype());
			vmbean.setPhoneno(input.getPhoneno());
			System.out.println(input.getDay() + " " + input.getVendorname() + " " + vmbean.getVendorAccount() + " " + vmbean.getVendorAddr() + " " + vmbean.getVendorWebsite() + " " + vmbean.getExpenses());
			if (!vendormgmtdao.update(session, vmbean)) {
				output.setMessage("Could not update vendor!");
				System.out.println("Could not update");
				output.setSuccess(false);
			} else {
				output.setMessage("Updated added successfully");
				output.setSuccess(true);
			}
		tx.commit();
		session.close();
		return output;
	}
}