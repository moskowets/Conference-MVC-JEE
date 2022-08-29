package eu.mktcode.app.datasource.database;

import eu.mktcode.app.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Deque;
import java.util.LinkedList;

public class ConnectionPool {

    private static ConnectionPool pool;
    private final Deque<Connection> connectionPool = new LinkedList<>();
    private final String url = Properties.getDataBaseURL();
    private final String user = Properties.getDataBaseUser();
    private final String password = Properties.getDataBasePassword();

    private static final int WAITING_TIME = Properties.getConnectionWaitingTime();

    public static synchronized ConnectionPool get() {
        if (pool == null)
            pool = new ConnectionPool();
        return pool;
    }
    private ConnectionPool() {

        for (int i = 0; i < Properties.getPoolSize(); i++) {
            connectionPool.push(createConnection());
        }
    }

    public synchronized PooledConnection getConnection() {
        while (connectionPool.size() == 0) {
            try {
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Connection connection = connectionPool.pop();
        try {
            //TODO change to isValid
            if (connection.isClosed())
                connection = createConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new PooledConnection(connection, connectionPool);
    }

    private Connection createConnection() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            //TODO logging
            throw new RuntimeException(e);
        }
    }

}
