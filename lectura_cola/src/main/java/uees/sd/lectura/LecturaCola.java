/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.lectura;

import com.google.gson.Gson;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import uees.sd.colamensaje.Cola;
import uees.sd.lectura.config.ConfigReader;
import uees.sd.lectura.infra.RestApiClient;
import uees.sd.lectura.storage.JsonFileWriter;
import uees.sd.lectura.util.HashUtil;

/**
 *
 * @author ninoska
 */
public class LecturaCola {
    
    private static ConfigReader configReader=new ConfigReader("config.properties");
    private static final Gson gson = new Gson();
    private static RestApiClient client = new RestApiClient();
    
    public static void main(String[] args) {
        //String UrlServidorActiveMq="tcp://localhost:61616";
        String UrlServidorActiveMq=configReader.getServidorQueue();
        String Queue=configReader.getNombreQueue();
        
        System.out.println("Servidor ACTIVE MQ: "+UrlServidorActiveMq);
        System.out.println("Queue: "+Queue);
        try {
            Cola.Suscriptor(UrlServidorActiveMq, Queue, new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    try {
                        String rutaActual = new java.io.File(".").getCanonicalPath();
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Formulario recibido: " + textMessage.getText());
                        CensusForm form=(CensusForm)gson.fromJson(textMessage.getText(),CensusForm.class);
                        System.out.println("Validando formulario...");
                        var valid=form.isValid();
                        var id=HashUtil.sha256(textMessage.getText());
                        if(valid){
                            System.out.println("Formulario valido");
                            var existe=client.exist(id);
                            if(existe){
                                System.out.println("El formulario ya existe en el modulo de almacenamiento.");
                                JsonFileWriter.guardarEnArchivoJson(form, rutaActual+"/Duplicados/"+id+".json");
                            }else{
                                var ResponseGuardar=client.createForm(form);
                                if(ResponseGuardar.getStatusInfo().toEnum()!=Status.CREATED){
                                    throw new Exception("Error al guardar formulario.");
                                }
                                System.out.println("Formulario guardado correctament.");
                            }
                            
                        }else{
                            System.out.println("Formulario no valido");
                            JsonFileWriter.guardarEnArchivoJson(form, rutaActual+"/Invalidos/"+id+".json");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        } catch (Exception e) {
            System.out.println("LecturaCola: " + e.getMessage());
        }
        
    }
}
