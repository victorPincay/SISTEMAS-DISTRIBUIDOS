/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sistema.distribuido.service;

/**
 *
 * @author ninoska
 */
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import uees.sistema.distribuido.config.ConfigReader;

public class LeaderElectionService {

    private static LeaderElectionService instance;
    private ConfigReader configReader;

    private LeaderElectionService() {
        configReader = new ConfigReader("config.properties");
    }

    public static synchronized LeaderElectionService getInstance() {
        if (instance == null) {
            instance = new LeaderElectionService();
        }
        return instance;
    }

    public void electLeader() {
        List<String> replicaArray = configReader.getReplicaAddresses();
        for (String replica : replicaArray) {
            if (checkHealth(replica)) {
                configReader.setProperty("leader", replica);
                break;
            }
        }
    }

    private boolean checkHealth(String uri) {
        Client client = ClientBuilder.newClient();
        try {
            Response response = client.target(uri + "/health")
                                      .request()
                                      .get();
            return response.getStatus() == 200;
        } catch (Exception e) {
            return false;
        } finally {
            client.close();
        }
    }
}
