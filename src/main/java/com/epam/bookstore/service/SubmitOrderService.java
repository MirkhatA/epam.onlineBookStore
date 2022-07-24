package com.epam.bookstore.service;

import com.epam.bookstore.dao.CartDao;
import com.epam.bookstore.dao.OrderDao;
import com.epam.bookstore.dao.daoImpl.CartDaoImpl;
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

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.MessageConstants.*;
import static com.epam.bookstore.constants.PageNameConstants.changePasswordJsp;
import static com.epam.bookstore.constants.PageNameConstants.ordersJsp;

public class SubmitOrderService implements Service {
    OrderDao orderDao = new OrderDaoImpl();
    CartDao cartDao = new CartDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long user_id = (Long) session.getAttribute(USER_ID);

        Order order = new Order();

        order.setUserId(user_id);
        order.setFullName((String) session.getAttribute(RECEIVER_NAME));
        order.setAddress((String) session.getAttribute(RECEIVER_ADDRESS));
        order.setMobile((String) session.getAttribute(RECEIVER_MOBILE));
        order.setComment((String) session.getAttribute(COMMENT));
        order.setPaymentWay((String) session.getAttribute(PAYMENT_WAY));
        order.setTotalPrice((Double) session.getAttribute(TOTAL_PRICE));
        orderDao.create(order);
        cartDao.deleteAllFromCart(user_id);

        req.setAttribute(ORDER_CREATED, ORDER_CREATED_MSG);
        dispatcher = req.getRequestDispatcher(ordersJsp);
        dispatcher.forward(req, res);
    }
}
