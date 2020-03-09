package controllers;
import models.User;
import db.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserRepository extends AbstractRepository<User> {
    public List<User> Users;

    public UserRepository(ConnectionPool connectionPool){
        super(connectionPool);
        Users = new LinkedList<>();
    }

    private static User getUser(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getDate(1), resultSet.getInt(2)
        );
        return user;
    }

    public void fillUsersList(ResultSet resultSet) throws SQLException {
        while(resultSet.next()){
            Users.add(getUser(resultSet));
        }
    }
    private void getQueryResult(ResultSet resultSet){
        try {
            this.fillUsersList(resultSet);
        } catch (SQLException e) {
        }
    }
    @Override
    public List<User> getAll() throws SQLException {
        return Users;
    }
    @Override
     public void getEntityBySum(int sum) throws SQLException
    {
        PreparedStatement preparedStatement = getPrepareStatement(
                "Select * FROM Ekz "+"Where sums>'"+Integer.toString(sum)+"'"
        );
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            getQueryResult(resultSet);
        } catch (SQLException e) {
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }
    public void getAllBD() throws SQLException
    {
        PreparedStatement preparedStatement = getPrepareStatement(
                "Select * FROM Ekz "
        );
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            getQueryResult(resultSet);
        } catch (SQLException e) {
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }
}
