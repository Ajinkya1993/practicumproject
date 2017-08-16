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
			
		//check if duplicate vendor exists
			VendorMgmtBean vmbean = new VendorMgmtBean();
			vmbean.setIdentity();
			vmbean.getIdentity().setVendorName(input.getVendorname());
			vmbean.getIdentity().setCircleId(input.getCircleId());
			VendorOutput vo_init = vendormgmtdao.checkVendors(session, vmbean);
			System.out.println("In add vendor with: "+input.getVendoraddress() + " "+input.getVendoraccountnumber() +" " + input.getVendorname() + " " +input.getCircleId() + " " + input.getExpenses() +" " + input.getMonth() );

			if (vo_init.isSuccess() == false) {
				output.setMessage(vo_init.getMessage());
				output.setSuccess(false);
				return output;
			}
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
			System.out.println("In add vendor with: "+input.getVendorname() + " " +input.getCircleId() + " " + input.getExpenses() +" " + input.getMonth() );
			//add new vendor (replicate rows with changing months)
			for(int i = 0; i < replication; i++) {
			vmbean.setIdentity();
			vmbean.getIdentity().setCircleId(input.getCircleId());
			newmonth = newmonth + 1;
			if(newmonth > 12) {
				newmonth = 1;
			}
			vmbean.getIdentity().setMonth(newmonth);
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
			System.out.println("Before creating vendor "+vmbean.getVendorAddr() + " "+vmbean.getVendorAccount());
			VendorOutput vo = vendormgmtdao.create(session, vmbean);
			if (vo.isSuccess() == false) {
				output.setMessage(vo.getMessage());
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
