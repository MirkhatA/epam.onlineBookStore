package com.epam.bookstore.service;

import com.epam.bookstore.dao.GenreDao;
import com.epam.bookstore.dao.daoImpl.GenreDaoImpl;

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
import static com.epam.bookstore.constants.PageNameConstants.EDIT_GENRE_JSP;
import static com.epam.bookstore.constants.PageNameConstants.ERROR_JSP;

public class ShowGenreEditDetailsService implements Service {
    GenreDao genreDao = new GenreDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Boolean isAdmin = (Boolean) session.getAttribute(IS_ADMIN);
        if (isAdmin != null && isAdmin.equals(true)) {
            Long genreId = Long.parseLong(req.getParameter(ID));
            List<String> genreParams = genreDao.getById(genreId);
            session.setAttribute(GENRE_ID, genreId);
            session.setAttribute(GENRE_PARAMS_EN, genreParams.get(ENGLISH_LANGUAGE_ID - 1));
            session.setAttribute(GENRE_PARAMS_RU, genreParams.get(RUSSIAN_LANGUAGE_ID - 1));
            dispatcher = req.getRequestDispatcher(EDIT_GENRE_JSP);
            dispatcher.forward(req, res);
        } else {
            dispatcher = req.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(req, res);
        }

    }
}
