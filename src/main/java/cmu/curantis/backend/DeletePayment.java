package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cmu.curantis.inputbeans.LoginInput;
import cmu.curantis.inputbeans.PaymentInput;
import cmu.curantis.outputbeans.LoginOutput;
import cmu.curantis.outputbeans.PaymentOutput;

@Path("/deletepayment")

public class DeletePayment {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public PaymentOutput login(PaymentInput input) {
		PaymentOutput output = new PaymentOutput();
		output.setCardNo(null);
		output.setSuccess(true);
		output.setMessage("Delete Payment Card Success!");
		return output;
	}
}
