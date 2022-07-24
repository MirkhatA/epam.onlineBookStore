package com.epam.bookstore.service;

import com.epam.bookstore.dao.BookDao;
import com.epam.bookstore.dao.daoImpl.BookDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.bookstore.constants.Constants.IS_ADMIN;
import static com.epam.bookstore.constants.PageNameConstants.allBooksJsp;
import static com.epam.bookstore.constants.PageNameConstants.errorJsp;

public class AdminAllBooksService implements Service {


    BookDao bookDao = new BookDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Boolean isAdmin = (Boolean) session.getAttribute(IS_ADMIN);

        if (isAdmin != null && isAdmin.equals(true)) {
            dispatcher = req.getRequestDispatcher(allBooksJsp);
            dispatcher.forward(req, res);
        } else {
            dispatcher = req.getRequestDispatcher(errorJsp);
            dispatcher.forward(req, res);
        }

    }
}
