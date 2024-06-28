/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.capturadatos;

import uees.sd.colamensaje.Cola;

/**
 *
 * @author ninoska
 */
public class EnviaDatos {
    
    public static void EnviarDatos(String UrlServidorActiveMq,String Queue,int cantidadmensajes){
        try {
                System.out.println("Inicia envio de "+cantidadmensajes+" mensajes.");
                CapturaDatos captura=new CapturaDatos();
                String mensaje="";
                for(int i=0;i<cantidadmensajes;i++){
                    mensaje=captura.GenerarFormulario();
                    System.out.println("Enviando mensaje ["+i+"] =>" +mensaje);
                    Cola.EnviarMensaje(UrlServidorActiveMq,Queue,mensaje);
                    System.out.println("Mensaje ["+i+"] enviado.");
                }
        } catch (Exception e) {
            System.out.println("EnviarDatos: "+e.getMessage());
        }

    }
}
