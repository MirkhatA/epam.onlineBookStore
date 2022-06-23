package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.OrderDao;
import com.epam.bookstore.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    ConnectionPool connectionPool;
    Connection connection;

    @Override
    public List<Order> getAll(int languageId) throws SQLException {
        return null;
    }

    @Override
    public Order getById(Long id, int languageId) throws SQLException {
        return null;
    }

    @Override
    public void create(Order order) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();



    }

    @Override
    public void update(Long id, Order order) throws SQLException {

    }
}
