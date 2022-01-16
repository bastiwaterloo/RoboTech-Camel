/*
 * 
 * Klasse erstellt von: Lukas Keﬂler
 * 
 */

package edu.thi.demo.konditionen;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


public class ReceiveConditionsFromQueue extends RouteBuilder {

	
    @Override
    public void configure() throws Exception {
        from("jms:queue:konditionenQueue")
        .process(new Processor() {
            public void process(Exchange exchange) throws Exception {
            long temp1 = (long) exchange.getIn().getHeader("temp1");
            long temp2 = (long) exchange.getIn().getHeader("temp2");
            long temp3 = (long) exchange.getIn().getHeader("temp3");
            long temp4 = (long) exchange.getIn().getHeader("temp4");
            exchange.getOut().setHeader(Exchange.HTTP_METHOD, "POST");
            exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
            exchange.getOut().setBody(
                    "{" 
                            +   "\"messageName\":\"aktuelleKonditionenMsg\","
                            +   "\"processVariables\":{" 
                            +       "\"temp1\":{"
                            +           "\"value\":" + temp1 + "," 
                            +           "\"type\":\"Long\"" + "},"  
                            +       "\"temp2\":{"
                            +           "\"value\":" + temp2 + "," 
                            +           "\"type\":\"Long\"" + "},"  
                            +       "\"temp3\":{"
                            +           "\"value\":" + temp3 + "," 
                            +           "\"type\":\"Long\"" + "},"  
                            +       "\"temp4\":{"
                            +           "\"value\":" + temp4 + "," 
                            +           "\"type\":\"Long\"" + "}"  
                            +   "}," 
                            +   "\"resultEnabled\":true"
                            + "}"
                        );
            }
            })
          // to rest api
            .to("http://localhost:8080/engine-rest/message");
    }
}
