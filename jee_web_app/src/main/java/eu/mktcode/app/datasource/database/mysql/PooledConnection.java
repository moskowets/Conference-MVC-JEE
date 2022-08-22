package eu.mktcode.app.datasource.database.mysql;

import java.sql.Connection;
import java.util.Deque;


public class PooledConnection implements AutoCloseable {

    private final Connection connection;
    private final Deque<Connection> pool;
    public PooledConnection(Connection connection, Deque<Connection> pool) {
        this.pool = pool;
        this.connection = connection;
    }
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        pool.push(connection);
    }
}
