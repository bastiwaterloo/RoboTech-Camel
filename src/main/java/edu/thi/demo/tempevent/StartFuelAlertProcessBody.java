package edu.thi.demo.tempevent;
/*
 * 
 * @Author Lukas Keﬂler
 * 
 */
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StartFuelAlertProcessBody implements Processor {

	//generate body for critical fuel event
    @Override
    public void process(Exchange exchange) throws Exception {
        long fuellstand1 = (long) exchange.getIn().getHeader("fuellstand1");
        long fuellstand2 = (long) exchange.getIn().getHeader("fuellstand2");
        long fuellstand3 = (long) exchange.getIn().getHeader("fuellstand3");
      
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
