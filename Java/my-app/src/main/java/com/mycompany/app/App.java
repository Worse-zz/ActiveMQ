/*
 * @Date: 2022-05-17 10:47:30
 * @LastEditors: Junxi ZHANG
 * @LastEditTime: 2022-05-18 16:40:12
 * @FilePath: /my-app/src/main/java/com/mycompany/app/App.java
 */
package com.mycompany.app;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class App {

    // URL of the JMS server. DEFAULT_BROKER_URL will just mean that JMS server is
    // on localhost
    private static String url = "tcp://172.16.16.10:62001";

    // default broker URL is : tcp://localhost:61616"
    private static String subject = "queue-2"; // Queue Name.You can create any/many queue names as per your requirement.

    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server and starting it
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Creating a non transactional session to send/receive JMS message.
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Destination represents here our queue 'JCG_QUEUE' on the JMS server.
        // The queue will be created automatically on the server.
        Destination destination = session.createQueue(subject);

        // MessageProducer is used for sending messages to the queue.
        MessageProducer producer = session.createProducer(destination);

        // We will send a small text message saying 'Hello World!!!'
        TextMessage message = session
                .createTextMessage("Hello !!! Welcome to the world of ActiveMQ.");

        // Here we are sending our message!
        producer.send(message);

        System.out.println("JCG printing@@ '" + message.getText() + "'");
        connection.close();
    }
}