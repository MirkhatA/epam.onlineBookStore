package com.epam.bookstore.dao;

import com.epam.bookstore.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> getAllByUserId(Long userId, Integer languageId) throws SQLException;

    void updateStatus(Long orderId, Long paidStatus, Long orderStatus) throws SQLException;


}
