package edu.thi.demo.tempevent;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StartTempAlertProcessBody implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // TODO Auto-generated method stub
        long temp1 = (long) exchange.getIn().getHeader("temp1");
        //long temp2 = (long) exchange.getIn().getHeader("temp2");

        exchange.getOut().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getOut()
        
        //TODO Vernünftige Nachricht übergeben
        .setBody(
              "{" 
            +   "\"messageName\":\"MaschinenfehlerMsg\","
            +   "\"processInstanceId\":\"9e63ef8e-7483-11ec-8281-00ff118a856c\","
            +   "\"processVariables\":{" 
            +       "\"temp1\":{"
            +           "\"value\":" + temp1 + "," 
            +           "\"type\":\"Long\"" + "}"  
            +   "}," 
            +   "\"resultEnabled\":true"
            + "}"
        );
    }

}
