/*
 * 
 * Klasse erstellt von: Lukas Keﬂler
 * 
 */

package edu.thi.demo.fehlendeAuftragsdaten;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.main.Main;

public class MainApp {
    
    private Main main;

    public static void main(String[] args) throws Exception {
        
        // This is an example how to run Camel in a standalone Java application.
        // It runs until CTRL-C terminates the JVM.
        MainApp mainProg = new MainApp();
        mainProg.boot();
    }
    
    public void boot() throws Exception {
        // create a LocationValidator instance
        main = new Main();
        
        //configure jms component        
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");

        // add the jms component to the context
        main.bind("jms",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        main.addRouteBuilder(new RouteBuilderApp());

        // run until you terminate the JVM
        System.out.println("Starting Camel. Use ctrl + c to terminate the JVM.\n");
        main.run();
    }

}
