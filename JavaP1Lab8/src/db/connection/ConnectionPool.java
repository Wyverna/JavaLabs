package db.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionPool {

    private String URL;
    private String USERID;
    private String PASSWORD;
    private Connection connection;
    private static Logger LOG = Logger.getLogger(ConnectionPool.class.toString());

    public ConnectionPool(String Url, String UserId, String password)
            throws SQLException {
        this.URL = Url;
        this.USERID = UserId;
        this.PASSWORD = password;
        this.connection = this.createConnection();

    }
    private Connection createConnection() throws SQLException {
        return DriverManager
                .getConnection(this.URL, this.USERID, this.PASSWORD);
    }
    public Connection getConnection(){
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }
}
