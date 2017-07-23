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
		CaregiverCircleDAO caregivercircledao = new CaregiverCircleDAO();
		CircleSubsDAO circlesubsdao = new CircleSubsDAO();
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		CaregiverCircleBean circleBean = caregivercircledao.getCircleInfo(session, email);
		if (!circleBean.getPrimaryCaregiver()) {
			output.setMessage("Sorry, Only Primary Caregiver Can Add Payment!");
			output.setSuccess(false);
		} else if (circleBean.getPrimaryCaregiver()) {
			CircleSubsBean circleSubsBean = new CircleSubsBean();
			circleSubsBean.setCardMemberFirstName(input.getCardmemberFirstName());
			circleSubsBean.setCardMemberMiddleName(input.getCardmemberMiddleName());
			circleSubsBean.setCardMemberLastName(input.getCardMemberLastName());
			circleSubsBean.setCardNumber(input.getCardNo());
			circleSubsBean.setCardType(input.getCardType());
			circleSubsBean.setCvvNo(input.getCvvNo());
			circleSubsBean.setExpirationDate(input.getExpirationDate());
			circlesubsdao.addCircleSubs(session, circleSubsBean);
			output.setMessage("Add Payment Success!");
			output.setSuccess(true);
		}
		tx.commit();
		session.close();
		return output;
	}
}
