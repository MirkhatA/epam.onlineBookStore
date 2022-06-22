package com.epam.bookstore.service;

import com.epam.bookstore.dao.UserDao;
import com.epam.bookstore.dao.daoImpl.UserDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.bookstore.constants.PageNameConstants.*;

public class EditPasswordService implements Service {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (!req.getParameter("newPassword").equals(req.getParameter("passwordRepeat"))) {
            req.setAttribute("passwordsAreDifferent", "These passwords are different");
            dispatcher = req.getRequestDispatcher(changePasswordJsp);
            dispatcher.forward(req, res);
        } else if (req.getParameter("oldPassword").isEmpty() || req.getParameter("newPassword").isEmpty() ||
                req.getParameter("passwordRepeat").isEmpty()) {
            req.setAttribute("emptyFields", "Please fill all data");
            dispatcher = req.getRequestDispatcher(changePasswordJsp);
            dispatcher.forward(req, res);
        } else if (!userDao.getUserPassword(userId).equals(req.getParameter("oldPassword"))) {
            req.setAttribute("oldPasswordIsIncorrect", "You need to write your old password!");
            dispatcher = req.getRequestDispatcher(changePasswordJsp);
            dispatcher.forward(req, res);
        } else {
            userDao.updatePassword(userId, req.getParameter("newPassword"));

            req.setAttribute("editPasswordSuccess", "You have successfully updated your password!");
            dispatcher = req.getRequestDispatcher(changePasswordJsp);
            dispatcher.forward(req, res);
        }
    }
}
