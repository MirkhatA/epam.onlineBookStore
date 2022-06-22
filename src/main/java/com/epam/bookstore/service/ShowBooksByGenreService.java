package com.epam.bookstore.service;

import com.epam.bookstore.dao.BookDao;
import com.epam.bookstore.dao.GenreDao;
import com.epam.bookstore.dao.daoImpl.BookDaoImpl;
import com.epam.bookstore.dao.daoImpl.GenreDaoImpl;
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

import static com.epam.bookstore.constants.PageNameConstants.mainJsp;

public class ShowBooksByGenreService implements Service {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        Long genreId = Long.parseLong(req.getParameter("id"));
        Integer languageId = (Integer) session.getAttribute("languageId");

        List<Book> books = bookDao.getBooksByGenreId(genreId, languageId);

        session.setAttribute("books", books);
        dispatcher = req.getRequestDispatcher(mainJsp);
        dispatcher.forward(req, res);
    }
}
