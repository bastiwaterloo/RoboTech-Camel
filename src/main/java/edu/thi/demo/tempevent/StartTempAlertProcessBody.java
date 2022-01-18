package edu.thi.demo.tempevent;

import javax.json.*;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StartTempAlertProcessBody implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // TODO Auto-generated method stub
        long fuellstand1 = (long) exchange.getIn().getHeader("fuellstand1");
        long fuellstand2 = (long) exchange.getIn().getHeader("fuellstand2");
        long fuellstand3 = (long) exchange.getIn().getHeader("fuellstand3");
        
        /*
		 JsonObject kritischeFuellstaendeJson = Json.createObjectBuilder()
     		 	.add("messageName", "MaschinenfehlerMsg")
     		 	.add("processInstanceId", "e8d7a6a0-7787-11ec-a920-00ff118a856c")
     			.add("processVariables", 
     				 Json.createObjectBuilder()       				 	
     				 .add("fuellstand1",
     	     				 Json.createObjectBuilder()       				 	
     						 .add("value", fuellstand1)
     						 .add("type", "Long")
     						 .build()
     					)     	     				        				 	    				 
     				 .add("fuellstand2",
     	     				 Json.createObjectBuilder()       				 	
     						 .add("value", fuellstand2)
     						 .add("type", "Long")     						 
     						 .build()
     				 )
     				.add("fuellstand3",
    	     				 Json.createObjectBuilder()       				 	
    						 .add("value", fuellstand3)
    						 .add("type", "Long")     						 
    						 .build()
    				 )
     	             .build()
     	         )
     			 .add("resultEnabled", true)
     	         .build();
	 
        
        exchange.getOut().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getOut().setBody(kritischeFuellstaendeJson);
        */
        
        exchange.getOut().setHeader(Exchange.HTTP_METHOD, "POST");
        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getOut().
        
        setBody(
              "{" 
            +   "\"messageName\":\"MaschinenfehlerMsg\","
            +   "\"processInstanceId\":\"a3ec8659-77f6-11ec-9b7a-2c56dcf95b9e\","
            +   "\"processVariables\":{" 
            +       "\"fuellstand1\":{"
            +           "\"value\":" + fuellstand1 + "," 
            +           "\"type\":\"Long\"" + "},"
            +		"\"fuellstand2\":{"
            +           "\"value\":" + fuellstand2 + "," 
            +           "\"type\":\"Long\"" + "},"
            +		"\"fuellstand3\":{"
            +           "\"value\":" + fuellstand3 + "," 
            +           "\"type\":\"Long\"" + "}"
            +   "}," 
            +   "\"resultEnabled\":true"
            + "}"
        );
        
    }

}
