/*
 * 
 * Klasse erstellt von: Lukas Ke�ler
 * 
 */

package edu.thi.demo.fehlendeAuftragsdaten;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;

public class RouteBuilderApp extends RouteBuilder {

	/*
	 * Idee f�r das Dateisystem:
	 * Alle Auftraege in XML konvertieren und an eines von 3 Arbeitsteams schicken
	 * Jedes Arbeitsteam ist f�r bestimmte Robotertypen verantwortlich, d.h. der
	 * Robotertyp ist das Hauptkriterium f�r die Sortierung
	 */
	
    @Override
    public void configure() throws Exception {
        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setRootName("missingDetails");

        // Input-Format: JSON
        // Target-Format: XML
        
        from("jms:queue:FehlendeAuftragsdatenQueue")
            .log("Fehlende Auftragsdaten \n${body}") //TODO ID dynamisch anlegen
            .unmarshal(xmlJsonFormat)
            .to("file:fehlendeAuftragsdaten?fileName=missingOrderDetails.xml"); //TODO Dateiname dynamisch anlegen
    }
}
