/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sistema.distribuido.infra;

/**
 *
 * @author ninoska
 */
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.Response.Status;
import uees.sistema.distribuido.config.ConfigReader;

public class RestApiClient {

    private ConfigReader configReader;

    private Client client;
    private WebTarget target;
    private ObjectMapper objectMapper;

    public RestApiClient() {
        configReader=new ConfigReader("config.properties");
        client = ClientBuilder.newClient();
        target = client.target(configReader.getUrlApiModuloStorage());
        objectMapper = new ObjectMapper();
    }

    public JsonNode getForms() throws Exception {
        Response response = target.path("/form")
                                  .request(MediaType.APPLICATION_JSON)
                                  .get();

        String json = response.readEntity(String.class);
        return objectMapper.readTree(json);
    }

    public JsonNode getFormById(String id) throws Exception {
        Response response = target.path("/form/" + id)
                                  .request(MediaType.APPLICATION_JSON)
                                  .get();

        String json = response.readEntity(String.class);
        return objectMapper.readTree(json);
    }
    
}
