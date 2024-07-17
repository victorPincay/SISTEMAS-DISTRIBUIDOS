/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.lectura.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import uees.sd.lectura.CensusForm;

/**
 *
 * @author ninoska
 */
public class JsonFileWriter {
    
    public static void guardarEnArchivoJson(CensusForm formulario, String rutaArchivo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Crear el archivo
            File archivo = new File(rutaArchivo);

            // Crear los directorios si no existen
            File directorio = archivo.getParentFile();
            if (directorio != null && !directorio.exists()) {
                directorio.mkdirs();
            }
            // Convertir el objeto a JSON y guardarlo en un archivo
            objectMapper.writeValue(archivo, formulario);
            System.out.println("Archivo JSON guardado en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
