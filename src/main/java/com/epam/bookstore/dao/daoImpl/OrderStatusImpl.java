package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.OrderStatusDao;
import com.epam.bookstore.entity.OrderStatus;
import com.epam.bookstore.entity.PaidStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusImpl implements OrderStatusDao {
    private final String GET_ALL_ITEMS = "SELECT * FROM order_status WHERE language_id=?";

    ConnectionPool connectionPool;
    Connection connection;

    @Override
    public List<OrderStatus> getAll(int languageId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<OrderStatus> orderStatusList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ITEMS)){
            ps.setInt(1, languageId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId(rs.getLong("id"));
                orderStatus.setName(rs.getString("name"));
                orderStatus.setLanguageId(rs.getInt("language_id"));
                orderStatusList.add(orderStatus);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return orderStatusList;
    }
}
