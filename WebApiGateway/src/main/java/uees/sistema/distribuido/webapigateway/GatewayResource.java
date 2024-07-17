/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sistema.distribuido.webapigateway;

/**
 *
 * @author ninoska
 */
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import uees.sistema.distribuido.config.ConfigReader;

@Path("/form")
public class GatewayResource {

    private List<String> replicas;
    private String leaderUri;
    private ConfigReader configReader;

    public GatewayResource(){
        configReader = new ConfigReader("config.properties");
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createForm(String formData) {
        return forwardRequest("/form", "POST", formData);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteForm(@PathParam("id") String id) {
        return forwardRequest("/form/" + id, "DELETE", null);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateForm(@PathParam("id") String id, String formData) {
        return forwardRequest("/form/" + id, "PUT", formData);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getForm(@PathParam("id") String id) {
        return forwardRequest("/form/" + id, "GET", null);
    }

    @GET
    @Path("/exist/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exist(@PathParam("id") String id) {
        return forwardRequest("/form/exist/" + id, "GET", null);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllForms() {
        return forwardRequest("/form", "GET", null);
    }

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFormsByCondition(String condition) {
        return forwardRequest("/form/query", "POST", condition);
    }

    private Response forwardRequest(String path, String method, String entity) {
        Client client = ClientBuilder.newClient();
        String leaderUri = configReader.getLeader();
        try {
            Response response;
            switch (method) {
                case "POST":
                    response = client.target(leaderUri + path)
                                     .request(MediaType.APPLICATION_JSON)
                                     .post(Entity.entity(entity, MediaType.APPLICATION_JSON));
                    break;
                case "PUT":
                    response = client.target(leaderUri + path)
                                     .request(MediaType.APPLICATION_JSON)
                                     .put(Entity.entity(entity, MediaType.APPLICATION_JSON));
                    break;
                case "DELETE":
                    response = client.target(leaderUri + path)
                                     .request(MediaType.APPLICATION_JSON)
                                     .delete();
                    break;
                case "GET":
                default:
                    response = client.target(leaderUri + path)
                                     .request(MediaType.APPLICATION_JSON)
                                     .get();
                    break;
            }
            return Response.status(response.getStatus()).entity(response.readEntity(String.class)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error forwarding the request").build();
        } finally {
            client.close();
        }
    }

    @GET
    @Path("/leader")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLeader() {
        String leaderUri = configReader.getLeader();
        return "{\"leaderUri\": \"" + leaderUri + "\"}";
    }
}
