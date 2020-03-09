package xml_work.xml_parse.marshalling;

import director.Director;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MyMarshal {
    public static void marshal(Director director){
        try {
            JAXBContext context = JAXBContext.newInstance(Director.class);

            Marshaller marshal = context.createMarshaller();
            marshal.marshal(director, new FileOutputStream("E:\\Program files\\JavaLab\\JavaP1Lab5\\src\\files\\workersMarsh.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
