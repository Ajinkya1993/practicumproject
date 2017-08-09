package cmu.curantis.backend;

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

@Path("/addvendor")
public class AddVendor {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public VendorOutput register(VendorInput input) {
		VendorOutput output = new VendorOutput();
		VendorMgmtDAO vendormgmtdao = new VendorMgmtDAO();
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		//not required to check if user belongs to that cirlce as it would be done before
		
		int replication = input.getReplicationfactor();
		int newmonth = input.getMonth()-1;
			VendorMgmtBean vmbean = new VendorMgmtBean();
			
			for(int i = 0; i < replication; i++) {
			vmbean.setIdentity();
			vmbean.getIdentity().setCircleId(input.getCircleId());
			newmonth = newmonth + 1;
			if(newmonth > 12) {
				newmonth = 1;
			}
			vmbean.getIdentity().setMonth(newmonth);
			vmbean.getIdentity().setVendorName(input.getVendorname());
			vmbean.setExpenses(input.getExpenses());
			vmbean.setVendorAccount(input.getVendoraccountnumber());
			vmbean.setVendorAddress(input.getVendoraddress());
			vmbean.setVendorWebsite(input.getVendorwebsite());

			if (!vendormgmtdao.create(session, vmbean)) {
				output.setMessage("Could not add vendor!");
				output.setSuccess(false);
			} else {
				output.setMessage("Vendor added successfully");
				output.setSuccess(true);
			}
			}
		tx.commit();
		session.close();
		return output;
	}
}
