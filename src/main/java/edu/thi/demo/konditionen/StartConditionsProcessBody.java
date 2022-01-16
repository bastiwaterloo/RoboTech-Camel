package edu.thi.demo.konditionen;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StartConditionsProcessBody implements Processor{


    @Override
    public void process(Exchange exchange) throws Exception {
        // TODO Auto-generated method stub
        exchange.getOut().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getOut()
        .setBody("{\r\n"
        		+ "  \"messageName\" : \"aktuelleKonditionenMsg\",\r\n"
        		+ "  \"processInstanceId\" : \"fa785387-76f4-11ec-b06a-2c56dcf95b9e\"\r\n"
        		+ "}");
    System.out.println(exchange.toString());
    }
}
