package controllers;
import db.ConnectionPool;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractController <E> {
    public Connection connection;
    public ConnectionPool connectionPool;

    public abstract List<E> getAll() throws SQLException;
    public abstract void getEntityById(String name) throws SQLException;

    public abstract boolean create(E entity) throws SQLException;

    public AbstractController(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
        this.connection = connectionPool.getConnection();
    }
    // Получение экземпляра PrepareStatement
    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
             }

        return ps;
    }
    // Закрытие PrepareStatement
    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                       }
        }
    }
}