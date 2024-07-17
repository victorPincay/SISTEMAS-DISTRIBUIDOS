/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sistema.distribuido.config;

/**
 *
 * @author ninoska
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigReader {

    private Properties properties = new Properties();

    public ConfigReader(String fileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getReplicaAddresses() {
        String replicas = properties.getProperty("replicas");
        if(replicas.isEmpty())
            return new ArrayList<>();
        return Arrays.asList(replicas.split(","));
    }
    public String getLeader() {
        String path = properties.getProperty("leader");
        return path;
    }
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        try (FileOutputStream out = new FileOutputStream(getClass().getClassLoader().getResource("config.properties").getFile())) {
            properties.store(out, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
