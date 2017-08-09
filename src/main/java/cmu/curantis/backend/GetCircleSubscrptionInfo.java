package cmu.curantis.backend;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import cmu.curantis.dao.CircleSubsDAO;
import cmu.curantis.dao.SessionUtil;
import cmu.curantis.entities.CircleSubsBean;
import cmu.curantis.inputbeans.PaymentInput;
import cmu.curantis.outputbeans.CircleSubscriptionOutput;

@Path("/getSubscriptionInfo") 
public class GetCircleSubscrptionInfo {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public CircleSubscriptionOutput register(PaymentInput input) {
		CircleSubscriptionOutput output = new CircleSubscriptionOutput();
		CircleSubsDAO circlesubsdao = new CircleSubsDAO();
		CircleSubsBean OcircleSubsBean = new CircleSubsBean();
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();
		CircleSubsBean circleSubsBean = new CircleSubsBean();
		circleSubsBean.setCircleId(input.getCircleId());
		OcircleSubsBean = circlesubsdao.getCircleSubs(session, circleSubsBean);
		if (OcircleSubsBean == null) {
			output.setMessage("Circle does not exist!");
			output.setSuccess(false);
			tx.commit();
			session.close();
			return output;
		} else {
			output.setCardmemberFirstName(OcircleSubsBean.getCardMemberFirstName());
			output.setCardMemberLastName(OcircleSubsBean.getCardMemberLastName());
			output.setCardNo(OcircleSubsBean.getCardNumber());
			output.setExpirationDate(OcircleSubsBean.getExpirationDate());
			output.setCardType(OcircleSubsBean.getCardType());
			output.setMessage("Received subscription parameters!");
			output.setSuccess(true);
			tx.commit();
			session.close();
			return output;
		}
	}
}
