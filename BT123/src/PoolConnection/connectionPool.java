package PoolConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface connectionPool {
    Connection getConnection() throws SQLException;
    boolean releaseConnection(Connection connection);
}
