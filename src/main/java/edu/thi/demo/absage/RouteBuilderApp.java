package edu.thi.demo.absage;
/*
 * 
 * @Authro Lukas Keﬂler
 * 
 */
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XmlJsonDataFormat;

public class RouteBuilderApp extends RouteBuilder {

	//Read from queue and write to filesystem
    @Override
    public void configure() throws Exception {
        XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
        xmlJsonFormat.setEncoding("UTF-8");
        xmlJsonFormat.setRootName("abgesagterAuftrag");

        // Input-Format: JSON
        // Ziel-Format: XML
        
        from("jms:queue:AbgesagteAuftraegeQueue")
            .log("Abgesagten Auftrag im Dateisystem ablegen...")
            .to("file:abgesagteAuftraege?fileName=abgesagterAuftrag.xml");
    }
}
