package edu.thi.demo.pda.integration;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;

public class RouteBuilderApp extends RouteBuilder {

	/*
	 * Idee für das Dateisystem:
	 * Alle Auftraege in XML konvertieren und an eines von 3 Arbeitsteams schicken
	 * Jedes Arbeitsteam ist für bestimmte Robotertypen verantwortlich, d.h. der
	 * Robotertyp ist das Hauptkriterium für die Sortierung
	 */
	
    @Override
    public void configure() throws Exception {
        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setRootName("item");

        // Input-Format: JSON
        // Target-Format: XML
        
        from("jms:queue:AuftragsdatenQueue")
          .choice()     
	        .when().jsonpath("$.item[0][?(@.roboterart == 'arm')]")
	            .log("Auftrag für Arbeitsteam 1 ablegen:\n${body}")
	            .unmarshal(xmlJsonFormat)
	            .to("file:auftraege/arbeitsteam1?fileName=order.xml") //TODO filename flexibel machen
	            
	        .when().jsonpath("$.item[0][?(@.roboterart == 'staubsauger')]")
	            .log("Auftrag für Arbeitsteam 2 ablegen:\n${body}")
	            .unmarshal(xmlJsonFormat)
	            .to("file:auftraege/arbeitsteam2?fileName=order.xml")
	            
	        .otherwise()
	            .log("Auftrag für Arbeitsteam 3 ablegen:\n${body}")
	            .unmarshal(xmlJsonFormat)
	            .to("file:auftraege/arbeitsteam3?fileName=order.xml");
    }
}
