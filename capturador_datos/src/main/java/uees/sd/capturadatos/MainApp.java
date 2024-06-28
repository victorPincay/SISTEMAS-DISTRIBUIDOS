/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.capturadatos;

import java.io.IOException;
import uees.sd.capturadatos.EnviaDatos;

/**
 *
 * @author ninoska
 */
public class MainApp {
    
    public static void main(String[] args) throws IOException {
        String UrlServidorActiveMq="tcp://localhost:61616";
        String Queue="colasd";
        int CantidadMensajes=10;
        
        System.out.println("Servidor: "+UrlServidorActiveMq);
        System.out.println("Cola: "+Queue);
        System.out.println("CantidadMensajes: "+CantidadMensajes);
        
        System.out.println("Press [enter] to start");
        System.in.read();
        
        EnviaDatos envio=new EnviaDatos();
        envio.EnviarDatos(UrlServidorActiveMq, Queue, CantidadMensajes);
    }
}
