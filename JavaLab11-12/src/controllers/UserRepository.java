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
                resultSet.getString(1), resultSet.getString(2),resultSet.getString(3)
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
     public void getEntityById(String name) throws SQLException
    {
        PreparedStatement preparedStatement = getPrepareStatement(
                "Select * FROM Users "+"Where Name="+name
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
                "Select * FROM Users "
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
    public void getEntityByIdandName(String name,String password) throws SQLException
    {
        PreparedStatement preparedStatement = getPrepareStatement(
                "Select * FROM Users "+"Where Name='"+name+"' and "+"Password='"+password+"'"
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
    public void Insert(String name,String password,String type) throws SQLException
    {
        PreparedStatement preparedStatement = getPrepareStatement(
                "Insert into Users(Name,Password,Type) values('"+name+"','"+password+"','"+type+"')"
        );
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
        finally {
            this.closePrepareStatement(preparedStatement);
        }
    }
    public boolean create(User entity) throws SQLException {
        return false;
    }
}
