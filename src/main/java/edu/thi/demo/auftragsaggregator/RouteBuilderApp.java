package edu.thi.demo.auftragsaggregator;
/*
 * 
 * @Author Sebastian Waterloo
 * 
 */

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;

public class RouteBuilderApp extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
		xmlJsonFormat.setEncoding("UTF-8");
		xmlJsonFormat.setRootName("auftraege");

		// Input-Format: JSON
		// Target-Format: XML

		from("jms:queue:AuftragsdatenQueue")
				.log("Auftrag ablegen:\n${body}").unmarshal(xmlJsonFormat)
				.to("file:auftraege?fileName=order.xml");
	}
}
