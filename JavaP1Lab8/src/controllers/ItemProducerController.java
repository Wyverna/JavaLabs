package controllers;

import db.connection.ConnectionPool;
import models.Item;
import models.Producer;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ItemProducerController extends AbstractController<Item, Producer> {
    private Logger LOG = Logger.getLogger(ItemController.class.toString());
    public List<Item> items;
    public List<Producer> producers;
    ItemController itemController;
    ProducerController producerController;

    public ItemProducerController(ConnectionPool connectionPool){
        super(connectionPool);
        items = new LinkedList<>();
        producers = new LinkedList<>();
        itemController = new ItemController(this.connectionPool);
        producerController = new ProducerController(this.connectionPool);
    }

    private static Producer getProducer(ResultSet resultSet) throws SQLException {
        Producer producer = new Producer(
                resultSet.getString(6), resultSet.getString(7)
        );
        return producer;
    }

    public void fillProducersList(ResultSet resultSet) throws SQLException {
        while(resultSet.next()){
            producers.add(getProducer(resultSet));
        }
    }
    private void getQueryResult(ResultSet resultSet){
        try {
            itemController.fillItemsList(items, resultSet);
            this.fillProducersList(resultSet);
            LOG.info(this.toString());
        } catch (SQLException e) {
            LOG.error(e);
        }
    }
    @Override
    public String toString(){
        return itemController.stringPlanets(items)+
                producerController.printSputniks(producers);
    }
    private String stringGetItemsWithCostless(int cost){
        return "SELECT * FROM Items p " +
                "LEFT JOIN Producers s " +
                "ON p.Producer=s.Producer " +
                "WHERE p.Cost<"+cost;
    }
    private String stringGetItemsWithName(String producer){
        return "SELECT * FROM Items p " +
                "LEFT JOIN Producers s " +
                "ON p.Producer=s.Producer " +
                "WHERE s.Producer In("+"'"+producer+"')";
    }
    private String stringCountry(String Country){
        return "SELECT * FROM Items p " +
                "LEFT JOIN Producers s " +
                "ON p.Producer=s.Producer "+
                "WHERE s.Country In("+"'"+Country+"')";
    }
    private String stringGetItemsWithYear(int year){
        return "SELECT * FROM Items p " +
                "LEFT JOIN Producers s " +
                "ON p.Producer=s.Producer " +
                "WHERE p.DateIssue between "+"'"+year+"-01-01' and "+"'"+(year+1)+"-01-01';";
    }
    public void getItemsWithCost(int cost){
        PreparedStatement preparedStatement = getPrepareStatement(
                stringGetItemsWithCostless(cost)
        );
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            getQueryResult(resultSet);
        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }
    public void getItemsWithCountry(String Country){
        PreparedStatement preparedStatement = getPrepareStatement(
                stringCountry(Country)
        );
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            getQueryResult(resultSet);
        } catch (SQLException e) {
            LOG.error(e);
        }
    }
    public void getItemsWithName(String producer){
        PreparedStatement preparedStatement = getPrepareStatement(
                stringGetItemsWithName(producer)
        );
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            getQueryResult(resultSet);
        } catch (SQLException e) {
            LOG.error(e);
        }
    }
    public void getItemsWithYear(int year){
        PreparedStatement preparedStatement = getPrepareStatement(
                stringGetItemsWithYear(year)
        );
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            getQueryResult(resultSet);
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    public void insertTransaction(Item item, Producer producer) throws SQLException {
        connectionPool.getConnection().setAutoCommit(false);
        itemController.create(item);
        producerController.create(producer);
        connection.rollback();
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return items;
    }

    @Override
    public Item getEntityById(Producer id) throws SQLException {
        return null;
    }

    @Override
    public void update(Producer oldId, Item newObject) throws SQLException {

    }

    @Override
    public void delete(Producer id) throws SQLException {
        PreparedStatement preparedStatement = getPrepareStatement(
                "Delete FROM Producers "+"Where Producer="+id.getProducer()
        );
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            getQueryResult(resultSet);
        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean create(Item entity) throws SQLException {
        return false;
    }
}
