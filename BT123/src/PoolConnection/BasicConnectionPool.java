package PoolConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements PoolConnection.connectionPool {
    private static String url = "jdbc:mysql://localhost:3306/thuctap";
    private static String user = "root";
    private static String password = "01072000";
    private static String Db_Driver = "com.mysql.jdbc.Driver";
    private static final int MAX_POOL_CONNECTION = 15;
    private List<Connection> connectionPools;
    private List<Connection> usedConnection = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;

    public BasicConnectionPool(List<Connection> pool) {
        this.connectionPools = pool;
    }

    public static BasicConnectionPool create() throws SQLException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for(int i = 0; i < 10; i++){
            pool.add(createConnection());
        }
        return new BasicConnectionPool(pool);
    }

    private static Connection createConnection() throws SQLException {
        System.out.println("Create Connection " );
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(connectionPools.isEmpty()){
            if(usedConnection.size() < MAX_POOL_CONNECTION){
                connectionPools.add(createConnection());
                System.out.println("Create new Connection");
            }
            else{
                throw new RuntimeException(
                        "Maximum pool size reached, no available connections!");
            }
        }
        Connection connections = connectionPools.remove(connectionPools.size()-1);
        usedConnection.add(connections);
        return connections;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPools.add(connection);
        return usedConnection.remove(connection);
    }

    public int getSize(){
        int size = connectionPools.size() + usedConnection.size();
        return size;
    }

    public void shutdown() throws SQLException {
        usedConnection.forEach(uses ->releaseConnection(uses));
        for(Connection c : connectionPools){
            c.close();
        }
        connectionPools.clear();
    }
}
