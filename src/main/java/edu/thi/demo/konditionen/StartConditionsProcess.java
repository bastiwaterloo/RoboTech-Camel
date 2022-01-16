package edu.thi.demo.konditionen;

import org.apache.camel.builder.RouteBuilder;

public class StartConditionsProcess extends RouteBuilder{
	   @Override
	    public void configure() throws Exception {
		   System.out.println("trying to send message ......");
	        from("jms:queue:konditionenQueue")
	        .process(new StartConditionsProcessBody())
	        .to("http://localhost:8080/engine-rest/message");
	    }
}
