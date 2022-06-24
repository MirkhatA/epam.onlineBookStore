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

import static com.epam.bookstore.constants.PageNameConstants.*;

public class SetOrderDataService implements Service{
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException,
            ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long userId = (Long) session.getAttribute("userId");
        Order order = new Order();
        setOrderData(order, req);

        if (order.getFullName().isEmpty() || order.getMobile().isEmpty() || order.getAddress().isEmpty() ||
                order.getPaymentWay().isEmpty()) {
            req.setAttribute("emptyFields", "Please fill all data");
            dispatcher = req.getRequestDispatcher(orderAddressJsp);
            dispatcher.forward(req, res);
        } else {
            session.setAttribute("receiverName", order.getFullName());
            session.setAttribute("receiverAddress", order.getAddress());
            session.setAttribute("receiverMobile", order.getMobile());
            session.setAttribute("comment", order.getComment());
            session.setAttribute("paymentWay", order.getPaymentWay());

            dispatcher = req.getRequestDispatcher(orderCheckoutJsp);
            dispatcher.forward(req, res);
        }
    }

    private void setOrderData(Order order, HttpServletRequest req) {
        order.setFullName(req.getParameter("fullName"));
        order.setMobile(req.getParameter("mobile"));
        order.setAddress(req.getParameter("address"));
        order.setComment(req.getParameter("comment"));
        order.setPaymentWay(req.getParameter("paymentWay"));
    }
}
