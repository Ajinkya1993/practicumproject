package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cmu.curantis.dao.caregiverCircleDAO;
import cmu.curantis.dao.CaregiverInfoDAO;
import cmu.curantis.dao.CircleSubsDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CaregiverCircleBean;
import cmu.curantis.entities.CaregiverInfoBean;
import cmu.curantis.entities.CircleSubsBean;
import cmu.curantis.inputbeans.PaymentInput;
import cmu.curantis.inputbeans.RegisterInput;
import cmu.curantis.outputbeans.LoginOutput;
import cmu.curantis.outputbeans.PaymentOutput;

@Path("/addpayment")
public class AddPayment {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PaymentOutput register(PaymentInput input) {
		PaymentOutput output = new PaymentOutput();
		caregiverCircleDAO caregivercircledao = new caregiverCircleDAO();
		CircleSubsDAO circlesubsdao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		CaregiverCircleBean circleBean = caregivercircledao.getByEmailAndId(session, input.getEmail(),
				input.getCircleid());
		if (circleBean == null) {
			output.setMessage("Circle Does Not Exist or You Are Not in the Circle!");
			output.setSuccess(false);
		} else if (!circleBean.getPrimaryCaregiver()) {
			output.setMessage("Sorry, Only Primary Caregiver Can Add Payment!");
			output.setSuccess(false);
		} else {
			CircleSubsBean circleSubsBean = new CircleSubsBean();
			circleSubsBean.setCircleId(input.getCircleid());
			circleSubsBean.setCardMemberFirstName(input.getCardmemberFirstName());
			circleSubsBean.setCardMemberMiddleName(input.getCardmemberMiddleName());
			circleSubsBean.setCardMemberLastName(input.getCardMemberLastName());
			circleSubsBean.setCardNumber(input.getCardNo());
			circleSubsBean.setCardType(input.getCardType());
			circleSubsBean.setCvvNo(input.getCvvNo());
			circleSubsBean.setExpirationDate(input.getExpirationDate());
			circlesubsdao.updateCircleSubs(session, circleSubsBean);
			if (!circlesubsdao.updateCircleSubs(session, circleSubsBean)) {
				output.setMessage("Circle does not exist!");
				output.setSuccess(false);
			} else {
				output.setMessage("Add Payment Success!");
				output.setSuccess(true);
			}
		}
		tx.commit();
		session.close();
		return output;
	}
}
