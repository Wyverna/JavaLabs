package xml_work.transform;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.util.logging.Logger;

public class XSLTransformer {
    private Logger LOG = Logger.getLogger(XSLTransformer.class.toString());
    public static void transform(){
        TransformerFactory xstf = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = xstf.newTransformer(new StreamSource("E:\\Program files\\JavaLab\\JavaP1Lab5\\src\\files\\information.xsl"));
            transformer.transform(new StreamSource("E:\\Program files\\JavaLab\\JavaP1Lab5\\src\\files\\workersMarsh.xml"),
                                new StreamResult("E:\\Program files\\JavaLab\\JavaP1Lab5\\src\\files\\information.html"));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
