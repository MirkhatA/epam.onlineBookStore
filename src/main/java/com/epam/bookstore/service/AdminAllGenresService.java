package com.epam.bookstore.service;

import com.epam.bookstore.dao.GenreDao;
import com.epam.bookstore.dao.daoImpl.GenreDaoImpl;
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

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.PageNameConstants.*;

public class AdminAllGenresService implements Service {
    GenreDao genreDao = new GenreDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Boolean isAdmin = (Boolean) session.getAttribute(IS_ADMIN);
        Integer languageId = (Integer) session.getAttribute(LANGUAGE_ID);

        if (isAdmin != null && isAdmin.equals(true)) {
            List<Genre> genreList = genreDao.getAll(languageId);

            session.setAttribute(GENRE_LIST, genreList);
            dispatcher = req.getRequestDispatcher(ALL_GENRES_JSP);
            dispatcher.forward(req, res);
        } else {
            dispatcher = req.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(req, res);
        }
    }
}
