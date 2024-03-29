package com.epam.bookstore.service;

import com.epam.bookstore.dao.AuthorDao;
import com.epam.bookstore.dao.BookDao;
import com.epam.bookstore.dao.GenreDao;
import com.epam.bookstore.dao.PublisherDao;
import com.epam.bookstore.dao.daoImpl.AuthorDaoImpl;
import com.epam.bookstore.dao.daoImpl.BookDaoImpl;
import com.epam.bookstore.dao.daoImpl.GenreDaoImpl;
import com.epam.bookstore.dao.daoImpl.PublisherDaoImpl;
import com.epam.bookstore.entity.Author;
import com.epam.bookstore.entity.Book;
import com.epam.bookstore.entity.Genre;
import com.epam.bookstore.entity.Publisher;

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

public class ShowMainPageService implements Service{
    private BookDao bookDao = new BookDaoImpl();
    private GenreDao genreDao = new GenreDaoImpl();
    private AuthorDao authorDao = new AuthorDaoImpl();

    private PublisherDao publisherDao = new PublisherDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);
        List<Genre> genreList = genreDao.getAll(languageId);

        List<Book> bookList = bookDao.getAllByStatus(languageId);
        List<Author> authorList = authorDao.getAll(languageId);
        List<Publisher> publisherList = publisherDao.getAll();

        session.setAttribute(GENRE_LIST, genreList);
        session.setAttribute(BOOK_LIST, bookList);
        session.setAttribute(AUTHOR_LIST, authorList);
        session.setAttribute(PUBLISHER_LIST, publisherList);

        dispatcher = req.getRequestDispatcher(MAIN_JSP);
        dispatcher.forward(req, res);
    }
}
