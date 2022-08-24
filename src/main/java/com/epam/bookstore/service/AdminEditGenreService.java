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
import java.util.ArrayList;
import java.util.List;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.MessageConstants.GENRE_UPDATED_SUCCESS_MSG;
import static com.epam.bookstore.constants.PageNameConstants.EDIT_GENRE_JSP;
import static com.epam.bookstore.constants.PageNameConstants.ERROR_JSP;

public class AdminEditGenreService implements Service {
    GenreDao genreDao = new GenreDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long genreId = (Long) session.getAttribute(GENRE_ID);
        Boolean isAdmin = (Boolean) session.getAttribute(IS_ADMIN);
        if (isAdmin != null && isAdmin.equals(true)) {
            System.out.println("id " + genreId);

            List<String> genreParams = new ArrayList<>();
            genreParams.add(req.getParameter("genreNameEn"));
            genreParams.add(req.getParameter("genreNameRu"));
            genreDao.update(genreParams, genreId);
            req.setAttribute(EDIT_GENRE_SUCCESS, GENRE_UPDATED_SUCCESS_MSG);
            dispatcher = req.getRequestDispatcher(EDIT_GENRE_JSP);
            dispatcher.forward(req, res);
        } else {
            dispatcher = req.getRequestDispatcher(ERROR_JSP);
            dispatcher.forward(req, res);
        }
    }
}
