package com.epam.bookstore.service;

import com.epam.bookstore.dao.BookDao;
import com.epam.bookstore.dao.daoImpl.BookDaoImpl;
import com.epam.bookstore.entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.PageNameConstants.bookDetailsJsp;

public class ShowBookDetailsService implements Service {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long bookId = Long.parseLong(req.getParameter(ID));
        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);

        Book book = bookDao.getById(bookId, languageId);

        session.setAttribute(BOOK, book);
        dispatcher = req.getRequestDispatcher(bookDetailsJsp);
        dispatcher.forward(req, res);
    }
}
