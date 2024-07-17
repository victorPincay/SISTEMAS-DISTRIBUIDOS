/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uess.sistema.distribuido.storage;

/**
 *
 * @author ninoska
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import uess.sistema.distribuido.config.ConfigReader;

public class FileStorage {

    private String STORAGE_FILE = "";
    private ObjectMapper objectMapper;
    private ConfigReader configReader;

    public FileStorage() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        configReader = new ConfigReader("replica-config.properties");
        STORAGE_FILE=configReader.getPath()+"/form_storage.json";
    }

    public Map<String, String> loadForms() {
        File file = new File(STORAGE_FILE);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, HashMap.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new HashMap<>();
    }

    public void saveForms(Map<String, String> forms) {
        try {
            objectMapper.writeValue(new File(STORAGE_FILE), forms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
