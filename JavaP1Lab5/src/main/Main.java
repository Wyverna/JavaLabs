package main;

import company.Company;
import director.Director;
import json_work.JsonGenerator;
import json_work.JsonReader;
import org.apache.log4j.Logger;
import serialization.Deserializator;
import serialization.MySerializator;
import worker.Admin;
import worker.Worker;
import worker.programmer.Junior;
import worker.programmer.Language;
import worker.programmer.Middle;
import xml_work.transform.XSLTransformer;
import xml_work.validate.ValidatorSAX;
import xml_work.xml_parse.demarshalling.SaxWorker;
import xml_work.xml_parse.marshalling.MyMarshal;

import java.io.InvalidClassException;


public class Main {
    final static Logger logger = Logger.getLogger(Main.class.toString());
    public static void main(String[] args) {

        //initialize Director and its workers
        Director director = Director.getInstance();
        Company company = new Company();
        director.company = company;
        Worker worker1 = new Worker("Шенец", "Полина", "Сергеевна", 12, 32);
        Worker worker2 = new Junior("Козущик", "Вячеслав", "Савельевич", 15, Language.Java);
        Worker worker3 = new Admin("Кривонос", "Тимур", "Александрович", 20, 56);
        Worker worker4 = new Middle("Савуль", "Вячеслав", "Евгеньевич", 5, Language.C);
        Director.Secretary secretary = new Director.Secretary("Гутник", "Владислав", "Владимирович", 12, 12);
        director.addWorker(worker1);
        director.addWorker(worker2);
        director.addWorker(worker3);
        director.addWorker(worker4);
       // director.addWorker(worker5);
        logger.info(director.countWorkers());
        director.printWorkers();
        //
        //Work with XML and XSD
        ValidatorSAX myValid = new ValidatorSAX();
        logger.info("nice");
        myValid.valide();
        MyMarshal.marshal(director);
        XSLTransformer.transform();
        SaxWorker.parse();

         MySerializator.serialization(worker1, "E:\\Program files\\JavaLab\\JavaP1Lab5\\src\\files\\serializeDirector.data");
        try {
            Worker worker = Deserializator.deserialization("E:\\Program files\\JavaLab\\JavaP1Lab5\\src\\files\\serializeDirector.data");
            logger.info(worker.toString());
        } catch (InvalidClassException e) {
            logger.error(e);

        }
        JsonReader.parser("E:\\Program files\\JavaLab\\JavaP1Lab5\\src\\files\\data.json");
        JsonGenerator.serialize(director,"E:\\Program files\\JavaLab\\JavaP1Lab5\\src\\files\\data1.json" );
    }
}
