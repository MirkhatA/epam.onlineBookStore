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
import java.util.List;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.MessageConstants.ORDER_CREATED_MSG;
import static com.epam.bookstore.constants.PageNameConstants.ORDER_JSP;

public class SubmitOrderService implements Service {
    OrderDao orderDao = new OrderDaoImpl();
    CartDao cartDao = new CartDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long userId = (Long) session.getAttribute(USER_ID);
        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);

        Order order = new Order();

        order.setUserId(userId);
        order.setFullName((String) session.getAttribute(RECEIVER_NAME));
        order.setAddress((String) session.getAttribute(RECEIVER_ADDRESS));
        order.setMobile((String) session.getAttribute(RECEIVER_MOBILE));
        order.setComment((String) session.getAttribute(COMMENT));
        order.setPaymentWay((String) session.getAttribute(PAYMENT_WAY));
        order.setTotalPrice((Double) session.getAttribute(TOTAL_PRICE));
        orderDao.create(order);
        cartDao.deleteAllFromCart(userId);

        List<Order> orderList = orderDao.getAllByUserId(userId, languageId);

        session.setAttribute(ORDER_LIST, orderList);
        req.setAttribute(ORDER_CREATED, ORDER_CREATED_MSG);
        dispatcher = req.getRequestDispatcher(ORDER_JSP);
        dispatcher.forward(req, res);
    }
}
