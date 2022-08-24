package com.epam.bookstore.service;

import com.epam.bookstore.dao.OrderDao;
import com.epam.bookstore.dao.OrderStatusDao;
import com.epam.bookstore.dao.PaidStatusDao;
import com.epam.bookstore.dao.daoImpl.OrderDaoImpl;
import com.epam.bookstore.dao.daoImpl.OrderStatusImpl;
import com.epam.bookstore.dao.daoImpl.PaidStatusDaoImpl;
import com.epam.bookstore.entity.Order;
import com.epam.bookstore.entity.OrderStatus;
import com.epam.bookstore.entity.PaidStatus;

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
import static com.epam.bookstore.constants.PageNameConstants.*;

public class AllOrdersService implements Service {
    OrderDao orderDao = new OrderDaoImpl();
    PaidStatusDao paidStatusDao = new PaidStatusDaoImpl();
    OrderStatusDao orderStatusDao = new OrderStatusImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);
        Boolean isAdmin = (Boolean) session.getAttribute(IS_ADMIN);

        if (isAdmin != null && isAdmin.equals(true)) {
            List<Order> orderList = orderDao.getAll(languageId);
            List<PaidStatus> paidStatusList = paidStatusDao.getAll(languageId);
            List<OrderStatus> orderStatusList = orderStatusDao.getAll(languageId);

            session.setAttribute(PAID_STATUS_LIST, paidStatusList);
            session.setAttribute(ORDER_LIST, orderList);
            session.setAttribute(ORDER_STATUS_LIST, orderStatusList);
            dispatcher = req.getRequestDispatcher(ALL_ORDERS_JSP);
            dispatcher.forward(req, res);
        } else {
            dispatcher = req.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(req, res);
        }

    }
}
