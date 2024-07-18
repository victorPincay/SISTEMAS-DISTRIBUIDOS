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
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import uees.sistema.distribuido.util.HashUtil;
import uess.sistema.distribuido.config.ConfigReader;
import uess.sistema.distribuido.storage.FileStorage;

@Path("/form")
public class FormResource {

    private FileStorage fileStorage;
    private Map<String, String> forms;
    private ConfigReader configReader;

    public FormResource() {
        fileStorage = new FileStorage();
        forms = fileStorage.loadForms();
        configReader = new ConfigReader("replica-config.properties");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createForm(String form) {
        try {
            String id = HashUtil.sha256(form);
            forms.put(id, form);
            fileStorage.saveForms(forms);
            Thread thread = new Thread(() -> replicatelocal("create", id, form));
            thread.start();
            return Response.status(Response.Status.CREATED).entity(id).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        
    }

    @DELETE
    @Path("/{id}")
    public Response deleteForm(@PathParam("id") String id) {
        if (forms.containsKey(id)) {
            forms.remove(id);
            fileStorage.saveForms(forms);
            Thread thread = new Thread(() -> replicatelocal("delete", id, null));
            thread.start();
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateForm(@PathParam("id") String id, String form) {
        if (forms.containsKey(id)) {
            forms.put(id, form);
            fileStorage.saveForms(forms);
            Thread thread = new Thread(() -> replicatelocal("update", id, form));
            thread.start();
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getForm(@PathParam("id") String id) {
        if (forms.containsKey(id)) {
            return Response.ok(forms.get(id)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/exist/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exist(@PathParam("id") String id) {
        if (forms.containsKey(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllForms() {
        return Response.ok(forms).build();
    }

    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFormsByCondition(String condition) {
        Map<String, String> result = forms.entrySet().stream()
                .filter(entry -> entry.getValue().contains(condition))
                .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), HashMap::putAll);
        return Response.ok(result).build();
    }
@POST
@Path("/replicate")
@Consumes(MediaType.APPLICATION_JSON)
public Response replicate(String payloadjson) {
    FormReplicationPayload payload=new Gson().fromJson(payloadjson, FormReplicationPayload.class);
    switch (payload.action) {
        case "create":
            forms.put(payload.id, payload.form);
            break;
        case "update":
            forms.put(payload.id, payload.form);
            break;
        case "delete":
            forms.remove(payload.id);
            break;
    }
    fileStorage.saveForms(forms);
    return Response.ok().build();
}


    private void replicatelocal(String action, String id, String form) {
        List<String> replicas = getReplicaAddresses();
        for (String replica : replicas) {
            try {
                Client client = ClientBuilder.newClient();
                WebTarget target = client.target(replica).path("/form/replicate");
                FormReplicationPayload payload = new FormReplicationPayload(action, id, form);
                String json=new Gson().toJson(payload);
                Response response = target.request(MediaType.APPLICATION_JSON)
                                          .post(Entity.entity(json, MediaType.APPLICATION_JSON));
                if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                    System.err.println("Failed to replicate to " + replica);
                }
            } catch (Exception e) {
                System.err.println("Failed to replicate to " + replica + ": " + e.getMessage());
            }
        }
    }

    private List<String> getReplicaAddresses() {
        // Retrieve addresses of other replicas, possibly from a configuration file or a service registry
        // For simplicity, let's return a static list
        return configReader.getReplicaAddresses();
    }

    public class FormReplicationPayload {
        public String action;
        public String id;
        public String form;

        public FormReplicationPayload(){
            
        }
        public FormReplicationPayload(String action, String id, String form) {
            this.action = action;
            this.id = id;
            this.form = form;
        }
    }
}

