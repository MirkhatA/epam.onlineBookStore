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

import static com.epam.bookstore.constants.MessageConstants.*;
import static com.epam.bookstore.constants.PageNameConstants.*;
import static com.epam.bookstore.constants.Constants.*;

public class AddItemToCartService implements Service {

    CartDao cartDao = new CartDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long bookId = Long.valueOf(req.getParameter(ID));
        Long userId = (Long) session.getAttribute(USER_ID);
        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);

        if (userId != null) {
            if (cartDao.getBookQuantityInCart(userId, bookId) > 0) {
                cartDao.increaseBookInCart(userId, bookId);
                List<Cart> cartList = cartDao.getCartByUserId(userId, languageId);
                Double totalPrice = cartDao.getTotalPriceFromCart(userId, languageId);
                session.setAttribute(CART_LIST, cartList);
                session.setAttribute(TOTAL_PRICE, totalPrice);
                dispatcher = req.getRequestDispatcher(CART_JSP);
                dispatcher.forward(req, res);
            } else {
                cartDao.addBookToCart(userId, bookId);
                req.setAttribute(ITEM_ADDED_TO_CART, ITEM_ADDED_TO_CART_MSG);
                dispatcher = req.getRequestDispatcher(MAIN_JSP);
                dispatcher.forward(req, res);
            }

        } else {
            req.setAttribute(PLEASE_LOGIN, PLEASE_LOGIN_MSG);
            dispatcher = req.getRequestDispatcher(LOGIN_JSP);
            dispatcher.forward(req, res);
        }
    }
}
