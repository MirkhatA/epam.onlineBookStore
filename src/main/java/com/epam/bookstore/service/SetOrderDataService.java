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

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.MessageConstants.*;
import static com.epam.bookstore.constants.PageNameConstants.*;

public class SetOrderDataService implements Service{
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException,
            ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long userId = (Long) session.getAttribute(USER_ID);
        Order order = new Order();
        setOrderData(order, req);

        if (order.getFullName().isEmpty() || order.getMobile().isEmpty() || order.getAddress().isEmpty() ||
                order.getPaymentWay().isEmpty()) {
            req.setAttribute(EMPTY_FIELDS, FILL_ALL_DATA_MSG);
            dispatcher = req.getRequestDispatcher(orderAddressJsp);
            dispatcher.forward(req, res);
        } else {
            session.setAttribute(RECEIVER_NAME, order.getFullName());
            session.setAttribute(RECEIVER_ADDRESS, order.getAddress());
            session.setAttribute(RECEIVER_MOBILE, order.getMobile());
            session.setAttribute(COMMENT, order.getComment());
            session.setAttribute(PAYMENT_WAY, order.getPaymentWay());

            dispatcher = req.getRequestDispatcher(orderCheckoutJsp);
            dispatcher.forward(req, res);
        }
    }

    private void setOrderData(Order order, HttpServletRequest req) {
        order.setFullName(req.getParameter(FULL_NAME));
        order.setMobile(req.getParameter(MOBILE));
        order.setAddress(req.getParameter(ADDRESS));
        order.setComment(req.getParameter(COMMENT));
        order.setPaymentWay(req.getParameter(PAYMENT_WAY));
    }
}
