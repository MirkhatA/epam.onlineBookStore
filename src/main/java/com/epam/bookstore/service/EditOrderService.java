package com.epam.bookstore.service;

import com.epam.bookstore.dao.OrderDao;
import com.epam.bookstore.dao.daoImpl.OrderDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.service.ServiceUrl.allOrdersService;

public class EditOrderService implements Service {
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;

        Long orderId = Long.valueOf(req.getParameter(ORDER_ID));
        Long paidStatus = Long.valueOf(req.getParameter(IS_PAID));
        Long orderStatus = Long.valueOf(req.getParameter(ORDER_STATUS));

        orderDao.updateStatus(paidStatus, orderStatus, orderId);
        dispatcher = req.getRequestDispatcher(allOrdersService);
        dispatcher.forward(req, res);
    }
}
