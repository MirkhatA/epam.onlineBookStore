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
import static com.epam.bookstore.constants.PageNameConstants.MAIN_JSP;

public class ShowBooksByAuthorService implements Service {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long authorId = Long.parseLong(req.getParameter(ID));
        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);

        List<Book> bookList = bookDao.getBooksByAuthorId(authorId, languageId);

        session.setAttribute(BOOK_LIST, bookList);
        dispatcher = req.getRequestDispatcher(MAIN_JSP);
        dispatcher.forward(req, res);
    }
}
