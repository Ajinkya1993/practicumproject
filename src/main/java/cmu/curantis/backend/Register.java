package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cmu.curantis.inputbeans.RegisterInput;
import cmu.curantis.outputbeans.LoginOutput;

@Path("/register")
public class Register {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public LoginOutput register(RegisterInput input) {
		LoginOutput output = new LoginOutput();
		output.setFirstName("Jing");
		output.setLastName("Zhu");
		output.setSuccess(true);
		output.setMessage("Register Success!");
		return output;
	}
}
