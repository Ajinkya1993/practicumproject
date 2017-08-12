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

@Path("/getvendorexpenses")
public class GetExpenses {
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
			vmbean.getIdentity().setVendorName(input.getVendorname());
			//get vendor info by name and circle id
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
