package cmu.curantis.backend;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cmu.curantis.inputbeans.CircleInput;
import cmu.curantis.outputbeans.CircleOutput;

@Path("/joincircle")
public class JoinCircle {
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public CircleOutput createCircle(CircleInput input) {
		CircleOutput output = new CircleOutput();
		output.setCircleId(1);
		output.setSuccess(true);
		output.setMessage("Circle joined!");
		return output;
	}
}
