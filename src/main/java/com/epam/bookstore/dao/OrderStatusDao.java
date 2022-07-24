package com.epam.bookstore.dao;

import com.epam.bookstore.entity.OrderStatus;

import java.sql.SQLException;
import java.util.List;

public interface OrderStatusDao {

    List<OrderStatus> getAll(int languageId) throws SQLException;

}
