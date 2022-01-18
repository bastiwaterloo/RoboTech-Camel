package edu.thi.demo.tempevent;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;



public class TempMainApp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Create Camel Context
        SimpleRegistry simpleReg = new SimpleRegistry();
        CamelContext context = new DefaultCamelContext(simpleReg);
        // Add ActiveMQ to Context
        ConnectionFactory connFac = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connFac));
        
        try {
            // Add Routes
            context.addRoutes(new StartTempAlertProcess());
            
            // Starts context in new thread
            context.start();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
