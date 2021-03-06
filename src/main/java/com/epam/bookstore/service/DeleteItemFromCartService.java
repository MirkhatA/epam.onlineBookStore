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

public class DeleteItemFromCartService implements Service {
    CartDao cartDao = new CartDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long bookId = Long.valueOf(req.getParameter("id"));
        Long userId = (Long) session.getAttribute("userId");
        Integer languageId = (Integer) session.getAttribute("languageId");

        if (userId != null) {
            cartDao.deleteBookFromCart(userId, bookId);
            List<Cart> cartList = cartDao.getCartByUserId(userId, languageId);
            Double totalPrice = cartDao.getTotalPriceFromCart(userId, languageId);
            session.setAttribute("cartList", cartList);
            session.setAttribute("totalPrice", totalPrice);
            dispatcher = req.getRequestDispatcher(cartJsp);
            dispatcher.forward(req, res);
        } else {
            req.setAttribute("pleaseLogin", "Please login");
            dispatcher = req.getRequestDispatcher(loginJsp);
            dispatcher.forward(req, res);
        }
    }
}
