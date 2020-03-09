package controllers;

import db.connection.ConnectionPool;
import models.Producer;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProducerController extends AbstractController<Producer, Integer> {
    public static String SELECT_ALL_Producer = "SELECT * FROM Producer";

    private static Logger LOG = Logger.getLogger(Producer.class.toString());

    public String printSputniks(List<Producer> producers){
        StringBuilder stringBuilder = new StringBuilder("");
        if(producers !=null) {
            for (Producer producer : producers
                    ) {
                stringBuilder.append(producer.toString());
            }
        }
        return stringBuilder.toString();
    }
        private void fillSqlQuery(PreparedStatement preparedStatement, Producer newProducer) throws SQLException {
        preparedStatement.setString(1, newProducer.getProducer());
        preparedStatement.setString(2, newProducer.getCountry());
    }

    private Producer getProducer(ResultSet resultSet) throws SQLException {
       Producer producer = new Producer(resultSet.getString(1),resultSet.getString(2));
        return producer;
    }

    public void fillProducersList(List<Producer> producers, ResultSet resultSet) throws SQLException {
        while(resultSet.next()){
            producers.add(getProducer(resultSet));
        }
    }
    @Override
    public List<Producer> getAll() throws SQLException {
        List<Producer> producers = new LinkedList<>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL_Producer);
        try{
            ResultSet resultSet = preparedStatement.executeQuery();
            fillProducersList(producers, resultSet);
        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
        return producers;
    }

    @Override
    public Producer getEntityById(Integer id) throws SQLException {
        Producer producer = null;
        PreparedStatement preparedStatement = getPrepareStatement(
                "SELECT * FROM Producers WHERE Producer=?"
        );
        try {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            producer = getProducer(resultSet);

        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
        return producer;
    }

    @Override
    public void update(Integer oldId, Producer newObject) throws SQLException {
        PreparedStatement preparedStatement = getPrepareStatement(
                "UPDATE Producers SET Name=?, Country=?"
        );
        try {
            fillSqlQuery(preparedStatement, newObject);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        PreparedStatement preparedStatement = getPrepareStatement(
                "DELETE FROM Producers WHERE Producer=?"
        );
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e);
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }

    @Override
    public boolean create(Producer entity) throws SQLException {
        PreparedStatement preparedStatement = getPrepareStatement(
                "INSERT INTO Producers values(?,?)"
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

    public ProducerController(ConnectionPool connectionPool) {
        super(connectionPool);
    }

}
