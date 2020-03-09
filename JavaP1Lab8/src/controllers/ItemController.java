package controllers;

import db.connection.ConnectionPool;
import models.Item;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ItemController extends AbstractController<Item, String>{
    public static String SELECT_ALL_PLANETS = "SELECT * FROM Items";
    private static Logger LOG = Logger.getLogger(ItemController.class.toString());

    public String stringPlanets(List<Item> items){
        StringBuilder stringBuilder = new StringBuilder("");
        if(items !=null) {
            for (Item item : items
                    ) {
                stringBuilder.append(item.toString());
            }
        }
        return stringBuilder.toString();
    }
    public ItemController(ConnectionPool connectionPool) {
        super(connectionPool);
    }
    private void fillSqlQuery(PreparedStatement preparedStatement, Item newItem) throws SQLException {
        preparedStatement.setString(1, newItem.getName());
        preparedStatement.setString(2, newItem.getRequest());
        preparedStatement.setString(3, newItem.getDateIssue());
        preparedStatement.setString(4, newItem.getProducer());
        preparedStatement.setInt(5, newItem.getCost());
    }

    public void fillItemsList(List<Item> items, ResultSet resultSet) throws SQLException {
        while(resultSet.next()){
            items.add(getItem(resultSet));
        }
    }

    private Item getItem(ResultSet resultSet) throws SQLException {
        Item item = new Item(
                resultSet.getString(1), resultSet.getString(2),
                resultSet.getString(3),resultSet.getString(4),
                resultSet.getInt(5)
        );
        return item;
    }
    //+
    @Override
    public List<Item> getAll() throws SQLException {
        List<Item> items = new LinkedList<>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL_PLANETS);
        try{
            ResultSet resultSet = preparedStatement.executeQuery();
            fillItemsList(items, resultSet);
        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
        return items;
    }
    //-
    @Override
    public Item getEntityById(String id){
        Item item = null;
        PreparedStatement preparedStatement = getPrepareStatement(
                "SELECT * FROM Items WHERE Name=?"
        );
        try {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            item = getItem(resultSet);

        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
        return item;
    }
    //+
    public void update(String oldId, Item newItem){
        PreparedStatement preparedStatement = getPrepareStatement(
                "UPDATE Items SET Name=?, Request=?, DateIssue=?, Producer=?, Cost=? where Name=?"
        );
        try {
            fillSqlQuery(preparedStatement, newItem);
            preparedStatement.setString(6, oldId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }
    //+
    @Override
    public void delete(String id) throws SQLException {
        PreparedStatement preparedStatement = getPrepareStatement(
                "DELETE FROM Items WHERE Name=?"
        );
        try {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }
//+
    @Override
    public boolean create(Item entity) throws SQLException {
        PreparedStatement preparedStatement = getPrepareStatement(
          "INSERT INTO Item values(?, ?, ?, ?, ?)"
        );
        try {
            fillSqlQuery(preparedStatement, entity);
            return preparedStatement.execute();

        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }
}
