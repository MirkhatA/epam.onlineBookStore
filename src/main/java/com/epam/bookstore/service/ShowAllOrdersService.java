package com.epam.bookstore.service;

import com.epam.bookstore.dao.OrderDao;
import com.epam.bookstore.dao.daoImpl.OrderDaoImpl;
import com.epam.bookstore.entity.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.PageNameConstants.ordersJsp;

public class ShowAllOrdersService implements Service {
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long userId = (Long) session.getAttribute(USER_ID);
        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);
        List<Order> orderList = orderDao.getAllByUserId(userId, languageId);

        session.setAttribute(ORDER_LIST, orderList);
        dispatcher = req.getRequestDispatcher(ordersJsp);
        dispatcher.forward(req, res);
    }
}
