package com.epam.bookstore.service;

import com.epam.bookstore.dao.UserDao;
import com.epam.bookstore.dao.daoImpl.UserDaoImpl;
import com.epam.bookstore.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.MessageConstants.*;
import static com.epam.bookstore.constants.PageNameConstants.*;

public class RegistrationService implements Service{
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;

        if (!req.getParameter(PASSWORD).equals(req.getParameter(REPEAT_PASSWORD))) {
            req.setAttribute(PASSWORDS_ARE_DIFFERENT, SAME_PASSWORDS_MSG);
            dispatcher = req.getRequestDispatcher(registrationJsp);
            dispatcher.forward(req, res);
        } else if (userDao.isEmailIsTaken(req.getParameter(EMAIL))) {
            req.setAttribute(EMAIL_IS_TAKEN, EMAIL_IS_TAKEN_MSG);
            dispatcher = req.getRequestDispatcher(registrationJsp);
            dispatcher.forward(req, res);
        } else if (userDao.isMobileTaken(req.getParameter(MOBILE))) {
            req.setAttribute(MOBILE_IS_TAKEN, PHONE_IS_TAKEN_MSG);
            dispatcher = req.getRequestDispatcher(registrationJsp);
            dispatcher.forward(req, res);
        } else {
            User user = new User();
            setUserData(user, req);
            if (user.getFirstName().isEmpty() || user.getMobile().isEmpty() || user.getEmail().isEmpty() ||
                    user.getPassword().isEmpty()) {
                req.setAttribute(EMPTY_FIELDS, FILL_ALL_DATA_MSG);
                dispatcher = req.getRequestDispatcher(registrationJsp);
                dispatcher.forward(req, res);
            } else {
                userDao.create(user);
                req.setAttribute(REGISTER_SUCCESS, REGISTER_SUCCESS_MSG);
                dispatcher = req.getRequestDispatcher(loginJsp);
                dispatcher.forward(req, res);
            }
        }
    }

    private void setUserData(User user, HttpServletRequest req) {
        user.setFirstName(req.getParameter(FIRST_NAME));
        user.setMobile(req.getParameter(MOBILE));
        user.setEmail(req.getParameter(EMAIL));
        user.setPassword(req.getParameter(PASSWORD));
    }
}
