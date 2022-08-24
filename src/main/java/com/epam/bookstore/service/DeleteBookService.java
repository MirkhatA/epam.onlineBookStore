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
import java.util.List;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.MessageConstants.BOOK_DELETE_SUCCESS_MSG;
import static com.epam.bookstore.constants.PageNameConstants.*;

public class DeleteBookService implements Service {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long bookId = Long.parseLong(req.getParameter(ID));
        Boolean isAdmin = (Boolean) session.getAttribute(IS_ADMIN);
        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);

        if (isAdmin != null && isAdmin.equals(true)) {
            bookDao.delete(bookId);
            List<Book> bookList = bookDao.getAll(languageId);
            session.setAttribute(BOOK_LIST, bookList);

            req.setAttribute(DELETE_BOOK_SUCCESS, BOOK_DELETE_SUCCESS_MSG);
            dispatcher = req.getRequestDispatcher(ALL_BOOKS_JSP);
            dispatcher.forward(req, res);
        } else {
            dispatcher = req.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(req, res);
        }
    }
}
