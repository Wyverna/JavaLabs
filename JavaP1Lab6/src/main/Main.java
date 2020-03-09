package main;

import company.Company;
import director.Director;
import org.apache.log4j.Logger;
import worker.Admin;
import worker.Worker;
import worker.programmer.Junior;
import worker.programmer.Language;
import worker.programmer.Middle;
import worker.programmer.Senior;


public class Main {
    final static Logger logger = Logger.getLogger(Main.class.toString());
    public static void main(String[] args) {
        Director director = Director.getInstance();
        Company company = new Company();
        director.company = company;
        Worker worker1 = new Worker("Бабако", "Анна", "Владимировна", 12, 32);
        Worker worker2 = new Junior("Козущик", "Вячеслав", "Савельевич", 15, Language.Java);
        Worker worker3 = new Admin("Кривонос", "Тимур", "Александрович", 20, 56);
        Worker worker4 = new Middle("Савуль", "Вячеслав", "Евгеньевич", 5, Language.C);
        Worker worker5 = new Senior("Горбачев", "Илья", "Павлович", 18, Language.Ruby);
        Director.Secretary secretary = new Director.Secretary("Кукареко", "Юлия", "Михайловна", 12, 12);


        director.addWorker(worker1);

        director.addWorker(worker2);
        director.addWorker(worker3);
        director.addWorker(worker4);
        director.addWorker(worker5);
        logger.info(director.countWorkers());
        director.printWorkers();
    }
}
