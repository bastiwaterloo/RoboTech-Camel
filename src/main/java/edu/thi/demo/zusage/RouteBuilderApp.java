/*
 * 
 * Klasse erstellt von: Lukas Keßler
 * 
 */

package edu.thi.demo.zusage;

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
        xmlJsonFormat.setRootName("zugesagterAuftrag");

        // Input-Format: JSON
        // Ziel-Format: XML
        
        from("jms:queue:ZugesagteAuftraegeQueue")
            .log("Zugesagten Auftrag im Dateisystem ablegen...") //TODO ID dynamisch anlegen
            .unmarshal(xmlJsonFormat)
            .to("file:zugesagteAuftraege?fileName=zugesagterAuftrag.xml"); //TODO Dateiname dynamisch anlegen
    }
}
