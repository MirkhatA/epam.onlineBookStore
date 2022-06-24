package com.epam.bookstore.service;

import com.epam.bookstore.dao.OrderDao;
import com.epam.bookstore.dao.daoImpl.OrderDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class EditOrderService implements Service {
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        Long paidStatus = Long.valueOf(req.getParameter("isPaid"));
        Long orderStatus = Long.valueOf(req.getParameter("orderStatus"));

        System.out.println(paidStatus);
        System.out.println(orderStatus);
        System.out.println(orderId);

        orderDao.updateStatus(paidStatus, orderStatus, orderId);
    }
}
