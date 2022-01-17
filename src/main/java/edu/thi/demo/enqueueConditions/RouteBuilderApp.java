/*
 * 
 * Klasse erstellt von: Lukas Ke�ler
 * 
 */

package edu.thi.demo.enqueueConditions;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;

public class RouteBuilderApp extends RouteBuilder {

	
	
    @Override
    public void configure() throws Exception {
        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setRootName("konditionen");

        // Input-Format: XML
        // Ziel-Format: JSON
        
        from("file:konditionen?fileName=preisaufschlaege_new.xml&noop=true")
            .log("Push conditions to queue") //TODO ID dynamisch anlegen
            .marshal().xmljson()
            .to("file:kondition_json?fileName=preisaufschlaege.json")
            .to("jms:queue:konditionenQueue"); //TODO Dateiname dynamisch anlegen
    }
}