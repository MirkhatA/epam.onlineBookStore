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

import static com.epam.bookstore.constants.Constants.IS_ADMIN;
import static com.epam.bookstore.constants.Constants.LANGUAGE_ID;
import static com.epam.bookstore.constants.PageNameConstants.errorJsp;

public class AddBookService implements Service {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Boolean isAdmin = (Boolean) session.getAttribute(IS_ADMIN);

        if (isAdmin != null && isAdmin.equals(true)) {
            List<Book> bookParams = new ArrayList<>();

            Book bookEng = new Book();
            setBookData(bookEng, req);
            bookEng.setTitle("bookTitleEn");
            bookEng.setDescription("bookDescriptionEn");
            bookParams.add(bookEng);

            Book bookRus = new Book();
            setBookData(bookRus, req);
            bookRus.setTitle("bookTitleRu");
            bookRus.setDescription("bookDescriptionRu");
            bookParams.add(bookRus);

            bookDao.create(bookParams);
        } else {
            dispatcher = req.getRequestDispatcher(errorJsp);
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
    }
}
