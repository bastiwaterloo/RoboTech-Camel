package edu.thi.demo.zusage;
/*
 * 
 * @Author Sebastian Waterloo
 * 
 */
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;

public class RouteBuilderApp extends RouteBuilder {
	
	//Get accepted orders from queue and save to filesystem
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
