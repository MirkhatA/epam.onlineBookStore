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

import static com.epam.bookstore.constants.PageNameConstants.changePasswordJsp;
import static com.epam.bookstore.constants.PageNameConstants.ordersJsp;

public class SubmitOrderService implements Service {
    OrderDao orderDao = new OrderDaoImpl();
    CartDao cartDao = new CartDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long user_id = (Long) session.getAttribute("userId");

        Order order = new Order();

        order.setUserId(user_id);
        order.setFullName((String) session.getAttribute("receiverName"));
        order.setAddress((String) session.getAttribute("receiverAddress"));
        order.setMobile((String) session.getAttribute("receiverMobile"));
        order.setComment((String) session.getAttribute("comment"));
        order.setPaymentWay((String) session.getAttribute("paymentWay"));
        order.setTotalPrice((Double) session.getAttribute("totalPrice"));
        orderDao.create(order);
        cartDao.deleteAllFromCart(user_id);

        req.setAttribute("orderCreated", "Your order created");
        dispatcher = req.getRequestDispatcher(ordersJsp);
        dispatcher.forward(req, res);
    }
}
