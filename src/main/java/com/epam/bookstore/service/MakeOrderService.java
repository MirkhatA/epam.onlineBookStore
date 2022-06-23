package com.epam.bookstore.service;

import com.epam.bookstore.dao.CartDao;
import com.epam.bookstore.dao.daoImpl.CartDaoImpl;
import com.epam.bookstore.entity.Cart;

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

public class MakeOrderService implements Service{
    CartDao cartDao = new CartDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long userId = (Long) session.getAttribute("userId");
        Integer languageId = (Integer) session.getAttribute("languageId");

        List<Cart> cartList = cartDao.getCartByUserId(userId, languageId);

        if (userId != null) {
            if (!cartList.isEmpty()) {
                dispatcher = req.getRequestDispatcher(orderAddressJsp);
                dispatcher.forward(req, res);
            } else {
                req.setAttribute("cartIsEmpty", "Your cart is empty");
                dispatcher = req.getRequestDispatcher(cartJsp);
                dispatcher.forward(req, res);
            }
        } else {
            dispatcher = req.getRequestDispatcher(errorJsp);
            dispatcher.forward(req, res);
        }
    }
}
