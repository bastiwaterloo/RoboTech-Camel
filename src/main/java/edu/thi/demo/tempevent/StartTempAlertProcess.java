package edu.thi.demo.tempevent;

import org.apache.camel.builder.RouteBuilder;



public class StartTempAlertProcess extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:KritischerFuellstandQueue")
        .log("Kritisches Füllstandereignis eingetreten!")
        .process(new StartTempAlertProcessBody())
        .to("http://localhost:8080/engine-rest/message");
    }

}