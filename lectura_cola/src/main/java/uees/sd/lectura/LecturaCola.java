/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.lectura;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import uees.sd.colamensaje.Cola;

/**
 *
 * @author ninoska
 */
public class LecturaCola {
    
    public static void main(String[] args) {
        String UrlServidorActiveMq="tcp://localhost:61616";
        String Queue="colasd";
        
        System.out.println("Servidor ACTIVE MQ: "+UrlServidorActiveMq);
        System.out.println("Queue: "+Queue);
        
        try {
            Cola.Suscriptor(UrlServidorActiveMq, Queue, new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    try {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Mensaje recibido: " + textMessage.getText());
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
