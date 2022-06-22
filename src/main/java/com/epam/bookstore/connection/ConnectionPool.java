package com.epam.bookstore.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {

    private String dbUrl;
    private String dbUser;
    private String dbPass;
    private String dbDriver;

    private ResourceBundle properties = ResourceBundle.getBundle("database");
    private final int maxPoolSize = 5;
    private static volatile ConnectionPool instance;
    private BlockingQueue<Connection> connectionQueue = new ArrayBlockingQueue<>(maxPoolSize);

    private ConnectionPool() {
        init();
    }

    private void init() {
        setConnectionData();
        loadDrivers();
        initPoolData();
    }

    private void setConnectionData() {
        this.dbUrl = properties.getString("dbUrl");
        this.dbUser = properties.getString("dbUser");
        this.dbPass = properties.getString("dbPass");
        this.dbDriver = properties.getString("dbDriver");
    }

    private void loadDrivers() {
        try {
            Driver driver = (Driver) Class.forName(dbDriver).newInstance();
        } catch (InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPool();
                }
            }
        }
        return localInstance;
    }

    private void initPoolData() {
        Connection connection;

        while (connectionQueue.size() < maxPoolSize) {
            try {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                connectionQueue.put(connection);
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public synchronized void returnConnection(Connection connection) {
        if ((connection != null) && (connectionQueue.size() <= maxPoolSize)) {
            try {
                connectionQueue.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
