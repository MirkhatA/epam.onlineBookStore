package com.epam.bookstore.service;

import com.epam.bookstore.dao.AuthorDao;
import com.epam.bookstore.dao.BookDao;
import com.epam.bookstore.dao.GenreDao;
import com.epam.bookstore.dao.daoImpl.AuthorDaoImpl;
import com.epam.bookstore.dao.daoImpl.BookDaoImpl;
import com.epam.bookstore.dao.daoImpl.GenreDaoImpl;
import com.epam.bookstore.entity.Author;
import com.epam.bookstore.entity.Book;
import com.epam.bookstore.entity.Genre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static com.epam.bookstore.constants.PageNameConstants.mainJsp;

public class ShowMainPageService implements Service{
    private BookDao bookDao = new BookDaoImpl();
    private GenreDao genreDao = new GenreDaoImpl();
    private AuthorDao authorDao = new AuthorDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        Integer languageId = (Integer) session.getAttribute("languageId");

        List<Genre> genres = genreDao.getAll(languageId);
        List<Book> books = bookDao.getAll(languageId);
        List<Author> authors = authorDao.getAll(languageId);

        session.setAttribute("genres", genres);
        session.setAttribute("books", books);
        session.setAttribute("authors", authors);

        dispatcher = req.getRequestDispatcher(mainJsp);
        dispatcher.forward(req, res);
    }
}
