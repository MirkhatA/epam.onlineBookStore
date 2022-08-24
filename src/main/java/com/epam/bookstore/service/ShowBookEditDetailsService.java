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
import java.util.ArrayList;
import java.util.List;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.PageNameConstants.editBookJsp;

public class ShowBookEditDetailsService implements Service {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long bookId = Long.parseLong(req.getParameter(ID));

        Book bookEn = bookDao.getById(bookId, ENGLISH_LANGUAGE_ID);
        Book bookRu = bookDao.getById(bookId, RUSSIAN_LANGUAGE_ID);

        session.setAttribute(BOOK_ID, bookId);
        session.setAttribute(BOOK_EN, bookEn);
        session.setAttribute(BOOK_RU, bookRu);
        dispatcher = req.getRequestDispatcher(editBookJsp);
        dispatcher.forward(req, res);
    }
}
