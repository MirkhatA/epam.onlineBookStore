package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.OrderDao;
import com.epam.bookstore.entity.Book;
import com.epam.bookstore.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final String CREATE_ORDER = "INSERT INTO `order` (total, paid_id, user_id, address, created_at, " +
            "status_id, comment, mobile, receiver_name, payment_way) VALUES (?,1,?,?,?,1,?,?,?,?);";
    private static final String GET_ALL_ORDERS_BY_USER_ID = "SELECT o.id, user_id, receiver_name, total, address, created_at, os.name as order_status, ps.name as paid_status, mobile, comment, payment_way FROM `order` o JOIN order_status os on o.status_id = os.id JOIN paid_status ps on ps.id = o.paid_id WHERE user_id=? AND os.language_id=? AND ps.language_id=? AND os.language_id=?;";

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

        Date date = Date.valueOf(java.time.LocalDate.now());

        try (PreparedStatement ps = connection.prepareStatement(CREATE_ORDER)){
            ps.setDouble(1, order.getTotalPrice());
            ps.setLong(2, order.getUserId());
            ps.setString(3, order.getAddress());
            ps.setDate(4, date);
            ps.setString(5, order.getComment());
            ps.setString(6, order.getMobile());
            ps.setString(7, order.getFullName());
            ps.setString(8, order.getPaymentWay());
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, Order order) throws SQLException {

    }

    @Override
    public List<Order> getAllByUserId(Long userId, Integer languageId) throws SQLException {
        List<Order> orders = new ArrayList<>();

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDERS_BY_USER_ID)) {
            ps.setLong(1, userId);
            ps.setInt(2, languageId);
            ps.setInt(3, languageId);
            ps.setInt(4, languageId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                setOrderData(order, rs);
                orders.add(order);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return orders;
    }

    private void setOrderData(Order order, ResultSet rs) throws SQLException {
        order.setId(rs.getLong("id"));
        order.setUserId(rs.getLong("user_id"));
        order.setFullName(rs.getString("receiver_name"));
        order.setTotalPrice(rs.getDouble("total"));
        order.setAddress(rs.getString("address"));
        order.setCreatedAt(rs.getDate("created_at"));
        order.setStatus(rs.getString("order_status"));
        order.setIsPaid(rs.getString("paid_status"));
        order.setMobile(rs.getString("mobile"));
        order.setComment(rs.getString("comment"));
        order.setPaymentWay(rs.getString("payment_way"));
    }
}
