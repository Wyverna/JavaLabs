package xml_work.xml_parse.demarshalling;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class DemoWorkerParser extends DefaultHandler{
    private static Logger LOG = Logger.getLogger(DemoWorkerParser.class.toString());
    @Override
    public void startDocument() throws SAXException{
        LOG.info("Doc started");
    }
    @Override
    public void endDocument() throws SAXException{
        LOG.info("\nDoc ended");
    }
    @Override
    public void startElement(String uri, String lovalName, String qname, Attributes attrs) throws SAXException{
        String str="";
        for(int i=0; i<attrs.getLength(); i++){
            str+=" "+attrs.getLocalName(i)+" = << " +
                    attrs.getValue(i);
            LOG.info(str.trim());
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{
        LOG.info(" "+localName);
    }
    @Override
    public void characters(char[] chars, int start, int length) throws  SAXException{
        LOG.info(new String(chars, start, length));
    }
}
