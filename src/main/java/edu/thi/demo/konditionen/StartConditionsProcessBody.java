package edu.thi.demo.konditionen;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StartConditionsProcessBody implements Processor{


    @Override
    public void process(Exchange exchange) throws Exception {
        // TODO Auto-generated method stub
        exchange.getOut().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
        Object payload = exchange.getIn().getBody(Object.class);
//        exchange.getOut()
//        .setBody("{\r\n"
//        		+ "  \"messageName\" : \"aktuelleKonditionenMsg\",\r\n"
//        		+ "  \"processInstanceId\" : \"69600847-7746-11ec-b8bd-261b7a4510c9\"\r\n"
//        		+ "}");
        exchange.getOut().setBody(payload);
        System.out.println(payload);
    System.out.println(exchange.toString());
    }
}
