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
import static com.epam.bookstore.constants.MessageConstants.BOOK_UPDATE_SUCCESS_MSG;
import static com.epam.bookstore.constants.PageNameConstants.*;

public class EditBookService implements Service{
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long bookId = (Long) session.getAttribute(BOOK_ID);
        Boolean isAdmin = (Boolean) session.getAttribute(IS_ADMIN);
        if (isAdmin != null && isAdmin.equals(true)) {
            List<Book> bookParams = new ArrayList<>();

            Book bookEng = new Book();
            bookEng.setTitle(req.getParameter(BOOK_TITLE_EN));
            bookEng.setDescription(req.getParameter(BOOK_DESCRIPTION_EN));
            bookEng.setLanguageId(ENGLISH_LANGUAGE_ID);
            setBookData(bookEng, req);
            bookParams.add(bookEng);

            Book bookRus = new Book();
            bookRus.setTitle(req.getParameter(BOOK_TITLE_RU));
            bookRus.setDescription(req.getParameter(BOOK_DESCRIPTION_RU));
            bookRus.setLanguageId(RUSSIAN_LANGUAGE_ID);
            setBookData(bookRus, req);
            bookParams.add(bookRus);

            bookDao.update(bookParams, bookId);
            req.setAttribute(EDIT_BOOK_SUCCESS, BOOK_UPDATE_SUCCESS_MSG);
            dispatcher = req.getRequestDispatcher(EDIT_BOOK_JSP);
            dispatcher.forward(req, res);
        } else {
            dispatcher = req.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(req, res);
        }
    }

    public static void setBookData(Book book, HttpServletRequest req) {
        book.setImage(req.getParameter("image"));
        book.setQuantity(Integer.valueOf(req.getParameter("bookQuantity")));
        book.setPrice(Double.valueOf(req.getParameter("bookPrice")));
        book.setAuthorId(Long.valueOf(req.getParameter("author")));
        book.setPublisherId(Long.valueOf(req.getParameter("publisher")));
        book.setGenreId(Long.valueOf(req.getParameter("genre")));
        book.setStatus(req.getParameter("status"));
    }
}
