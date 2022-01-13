package edu.thi.demo.tempevent;

import org.apache.camel.builder.RouteBuilder;

import edu.thi.demo.tempevent.StartTempAlertProcessBody;

public class StartTempAlertProcess extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:CriticalTempEventQueue")
        .log("Kritisches Maschinenereignis eingetreten!")
        .process(new StartTempAlertProcessBody())     
        .to("http://localhost:8080/engine-rest/message");
    }

}