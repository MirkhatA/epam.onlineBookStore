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

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.PageNameConstants.CART_JSP;

public class ShowCartService implements Service{
    CartDao cartDao = new CartDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long userId = (Long) session.getAttribute(USER_ID);
        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);

        List<Cart> cartList = cartDao.getCartByUserId(userId, languageId);
        Double totalPrice = cartDao.getTotalPriceFromCart(userId, languageId);
        session.setAttribute(TOTAL_PRICE, totalPrice);
        session.setAttribute(CART_LIST, cartList);
        dispatcher = req.getRequestDispatcher(CART_JSP);
        dispatcher.forward(req, res);
    }
}
