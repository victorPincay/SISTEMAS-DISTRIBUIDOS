package uees.sistema.distribuido.webapigateway;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("sample")
public class SampleResource {

	@GET
	public Response message() {
		return Response.ok("hola").build();
	}

}
