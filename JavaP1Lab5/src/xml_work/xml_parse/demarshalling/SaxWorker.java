package xml_work.xml_parse.demarshalling;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SaxWorker {
    private static Logger LOG = Logger.getLogger(SaxWorker.class);
    public static void parse(){
        XMLReader reader;
        {
            try {
                reader = XMLReaderFactory.createXMLReader();
                DemoWorkerParser handler = new DemoWorkerParser();
                reader.setContentHandler(handler);
                reader.parse("E:\\Program files\\JavaLab\\JavaP1Lab5\\src\\files\\workersMarsh.xml");
            } catch (SAXException e) {
                LOG.error("Error of SAX parser" + e);
            } catch (IOException e) {
                LOG.error("Error of I/O Stream" + e);
            }
        }
    }
}
