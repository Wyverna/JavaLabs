package controllers;

import db.connection.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractController <E, K> {
    public Connection connection;
    public ConnectionPool connectionPool;
    private static Logger LOG = Logger.getLogger(ConnectionPool.class.toString());

    public abstract List<E> getAll() throws SQLException;
    public abstract E getEntityById(K id) throws SQLException;
    public abstract void update(K oldId, E newObject) throws SQLException;
    public abstract void delete(K id) throws SQLException;
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
            LOG.error(e);
        }

        return ps;
    }
    // Закрытие PrepareStatement
    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                LOG.error(e);
            }
        }
    }
}