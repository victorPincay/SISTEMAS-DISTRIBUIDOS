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
import java.io.IOException;
import java.io.InputStream;
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

    public String getServidorQueue() {
        String servidor = properties.getProperty("servidorqueue");
        return servidor;
    }
    public String getNombreQueue() {
        String servidor = properties.getProperty("nombrequeue");
        return servidor;
    }
    public String getUrlApiModuloStorage() {
        String servidor = properties.getProperty("urlapimodulostorage");
        return servidor;
    }
}
