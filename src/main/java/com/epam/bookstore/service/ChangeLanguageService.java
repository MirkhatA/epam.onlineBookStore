package com.epam.bookstore.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.bookstore.constants.PageNameConstants.*;

public class ChangeLanguageService implements Service {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Long languageId = Long.parseLong(req.getParameter("id"));

        if (languageId == 1) {
            session.setAttribute("languageName", "en");
            session.setAttribute("languageId", 1);
        } else if (languageId == 2) {
            session.setAttribute("languageName", "ru");
            session.setAttribute("languageId", 2);

        }

        session.setAttribute("languageId", languageId);
        req.getRequestDispatcher(indexJsp).forward(req, res);
    }
}
