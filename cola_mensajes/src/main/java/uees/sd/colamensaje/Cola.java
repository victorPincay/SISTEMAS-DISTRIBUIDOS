/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uees.sd.colamensaje;

import java.io.IOException;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Cola {

    public static void EnviarMensaje(String UrlServerActiveMq,String Queue, String Mensaje) {
        try {
            // Conexión a ActiveMQ
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(UrlServerActiveMq);
            Connection connection = factory.createConnection();
            connection.start();

            // Crear una sesión
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(Queue);

            // Crear un productor de mensajes
            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage(Mensaje);

            // Enviar el mensaje
            producer.send(message);
            // Cerrar la conexión
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("EnviarMensaje: " + e.getMessage());
        }

    }

    public static String ObtenerMensaje(String UrlServerActiveMq,String Queue) {
        try {
            // Conexión a ActiveMQ
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(UrlServerActiveMq);
            Connection connection = factory.createConnection();
            connection.start();

            // Crear una sesión
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(Queue);

            // Crear un consumidor de mensajes
            MessageConsumer consumer = session.createConsumer(queue);

            // Recibir el mensaje
            TextMessage message = (TextMessage) consumer.receive();

            String mensaje = message.getText();

            // Cerrar la conexión
            consumer.close();
            session.close();
            connection.close();
            return mensaje;

        } catch (Exception e) {
            System.out.println("ObtenerMensaje: " + e.getMessage());
            return null;
        }
    }
    
    public static void Suscriptor(String UrlServerActiveMq,String Queue,MessageListener listener) throws JMSException, IOException{
        // Conexión a ActiveMQ
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(UrlServerActiveMq);
        Connection connection = factory.createConnection();
        connection.start();

        // Crear una sesión
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(Queue);

        // Crear un consumidor de mensajes
        MessageConsumer consumer = session.createConsumer(queue);

        // Configurar el listener para recibir mensajes en tiempo real
        consumer.setMessageListener(listener);

        // Mantener la aplicación corriendo para recibir mensajes
        System.out.println("Press [enter] to exit");
        System.in.read();

        // Cerrar la conexión
        consumer.close();
        session.close();
        connection.close();
    
    }
}
