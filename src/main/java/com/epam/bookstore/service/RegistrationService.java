package com.epam.bookstore.service;

import com.epam.bookstore.dao.UserDao;
import com.epam.bookstore.dao.daoImpl.UserDaoImpl;
import com.epam.bookstore.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

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
import static com.epam.bookstore.validator.UserValidator.*;

public class RegistrationService implements Service{
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;

        User user = new User();
        setUserData(user, req);

        if (!req.getParameter(PASSWORD).equals(req.getParameter(REPEAT_PASSWORD))) {
            req.setAttribute(PASSWORDS_ARE_DIFFERENT, SAME_PASSWORDS_MSG);
            dispatcher = req.getRequestDispatcher(REGISTRATION_JSP);
            dispatcher.forward(req, res);
        } else if (userDao.isEmailIsTaken(req.getParameter(EMAIL))) {
            req.setAttribute(EMAIL_IS_TAKEN, EMAIL_IS_TAKEN_MSG);
            dispatcher = req.getRequestDispatcher(REGISTRATION_JSP);
            dispatcher.forward(req, res);
        } else if (userDao.isMobileTaken(req.getParameter(MOBILE))) {
            req.setAttribute(MOBILE_IS_TAKEN, PHONE_IS_TAKEN_MSG);
            dispatcher = req.getRequestDispatcher(REGISTRATION_JSP);
            dispatcher.forward(req, res);
        } else if (!isDataFilled(user)) {
            req.setAttribute(EMPTY_FIELDS, FILL_ALL_DATA_MSG);
            dispatcher = req.getRequestDispatcher(REGISTRATION_JSP);
            dispatcher.forward(req, res);
        } else {
            userDao.create(user);
            req.setAttribute(REGISTER_SUCCESS, REGISTER_SUCCESS_MSG);
            dispatcher = req.getRequestDispatcher(LOGIN_JSP);
            dispatcher.forward(req, res);
        }
    }

    public static void setUserData(User user, HttpServletRequest req) {
        user.setFirstName(req.getParameter(FIRST_NAME));
        user.setLastName(req.getParameter(LAST_NAME));
        user.setMobile(req.getParameter(MOBILE));
        user.setEmail(req.getParameter(EMAIL));
        user.setAddress(req.getParameter(ADDRESS));
        String password = DigestUtils.md5Hex(req.getParameter(PASSWORD));
        user.setPassword(password);
    }
}
