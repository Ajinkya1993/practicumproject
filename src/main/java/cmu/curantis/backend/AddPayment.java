package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cmu.curantis.inputbeans.PaymentInput;
import cmu.curantis.inputbeans.RegisterInput;
import cmu.curantis.outputbeans.LoginOutput;
import cmu.curantis.outputbeans.PaymentOutput;

public class AddPayment {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public PaymentOutput register(PaymentInput input) {
		PaymentOutput output = new PaymentOutput();
		output.setSuccess(true);
		output.setMessage("Add Payment Success!");
		return output;
	}
}
