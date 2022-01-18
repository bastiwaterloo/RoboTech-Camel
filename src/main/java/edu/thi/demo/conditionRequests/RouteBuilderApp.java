/*
 * 
 * Klasse erstellt von: Lukas Ke�ler
 * 
 */

package edu.thi.demo.conditionRequests;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;

public class RouteBuilderApp extends RouteBuilder {

	/*
	 * Idee f�r das Dateisystem: Alle Auftraege in XML konvertieren und an eines von
	 * 3 Arbeitsteams schicken Jedes Arbeitsteam ist f�r bestimmte Robotertypen
	 * verantwortlich, d.h. der Robotertyp ist das Hauptkriterium f�r die Sortierung
	 */

	@Override
	public void configure() throws Exception {
		XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
		xmlJsonFormat.setEncoding("UTF-8");
		xmlJsonFormat.setRootName("conditionRequestQueue");

		// Input-Format: JSON
		// Target-Format: XML

		from("jms:queue:conditionRequestQueue")
				.log("ProcessinstanceId:\n${body}").unmarshal(xmlJsonFormat)
				.to("file:processinstancIds?fileName=instance.xml");
	}
}
