/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sistema.distribuido.webapimodulostorage;

/**
 *
 * @author ninoska
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkHealth() {
        return Response.ok("OK").build();
    }
}
