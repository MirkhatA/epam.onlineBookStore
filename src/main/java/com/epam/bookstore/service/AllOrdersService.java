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
import com.epam.bookstore.entity.User;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.bookstore.constants.PageNameConstants.*;

public class AllOrdersService implements Service {
    OrderDao orderDao = new OrderDaoImpl();
    PaidStatusDao paidStatusDao = new PaidStatusDaoImpl();
    OrderStatusDao orderStatusDao = new OrderStatusImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Integer languageId = (Integer) session.getAttribute("languageId");
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

        if (isAdmin != null && isAdmin.equals(true)) {
            List<Order> orderList = orderDao.getAll(languageId);
            List<PaidStatus> paidStatuses = paidStatusDao.getAll(languageId);
            List<OrderStatus> orderStatuses = orderStatusDao.getAll(languageId);

            session.setAttribute("paidStatuses", paidStatuses);
            session.setAttribute("orderList", orderList);
            session.setAttribute("orderStatuses", orderStatuses);
            dispatcher = req.getRequestDispatcher(allOrdersJsp);
            dispatcher.forward(req, res);
        } else {
            dispatcher = req.getRequestDispatcher(errorJsp);
            dispatcher.forward(req, res);
        }

    }
}
