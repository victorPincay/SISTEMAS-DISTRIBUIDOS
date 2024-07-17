/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.capturadatos;

import java.io.IOException;
import java.util.Scanner;
import uees.sd.capturadatos.EnviaDatos;
import uees.sd.capturadatos.config.ConfigReader;

/**
 *
 * @author ninoska
 */
public class MainApp {
    private static ConfigReader configReader=new ConfigReader("config.properties");

    public static void main(String[] args) throws IOException {
        String UrlServidorActiveMq=configReader.getServidorQueue();
        String Queue=configReader.getNombreQueue();
        
        System.out.println("Servidor: "+UrlServidorActiveMq);
        System.out.println("Cola: "+Queue);
        
        Iniciar(UrlServidorActiveMq,Queue);
        
    }
    
    private static void Iniciar(String UrlServidorActiveMq,String Queue) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de mensajes que desea enviar.");
        var CantidadMensajes=scanner.nextInt();
        
        System.out.println("Press [enter] to start");
        System.in.read();
        
        EnviaDatos envio=new EnviaDatos();
        envio.EnviarDatos(UrlServidorActiveMq, Queue, CantidadMensajes);
        
        Iniciar(UrlServidorActiveMq,Queue);
    }
}
