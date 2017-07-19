package cmu.curantis.backend;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/hello")
public class HelloWorldService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Hi " + msg + ". Welcome to Curantis Solutions Backend Service";
 
		return Response.status(200).entity(output).build();
 
	}
 
}