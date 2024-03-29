package com.epam.bookstore.service;

import com.epam.bookstore.dao.UserDao;
import com.epam.bookstore.dao.daoImpl.UserDaoImpl;
import com.epam.bookstore.entity.User;
import com.epam.bookstore.validator.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.MessageConstants.WRONG_LOGIN_OR_PASS_MSG;
import static com.epam.bookstore.constants.PageNameConstants.*;

public class LoginService implements Service {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        String login = req.getParameter(LOGIN);
        String password = DigestUtils.md5Hex(req.getParameter(PASSWORD));

        User user = userDao.getUserByLoginAndPassword(login, password);

        if (user != null) {
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(FIRST_NAME, user.getFirstName());
            session.setAttribute(LAST_NAME, user.getLastName());
            session.setAttribute(EMAIL, user.getEmail());
            session.setAttribute(ADDRESS, user.getAddress());
            session.setAttribute(MOBILE, user.getMobile());
            session.setAttribute(REGISTERED_DATE, user.getRegisteredAt());
            session.setAttribute(ROLE_ID, user.getRoleId());
            session.setAttribute(STATUS, user.getStatus());

            if (user.getStatus().equals(INACTIVE)) {
                session.setAttribute(IS_BLOCKED, true);
                dispatcher = req.getRequestDispatcher(LOGIN_JSP);
                dispatcher.forward(req, res);
            } else if (UserValidator.isAdmin(user.getRoleId())) {
                session.setAttribute(IS_ADMIN, true);
                dispatcher = req.getRequestDispatcher(MAIN_JSP);
                dispatcher.forward(req, res);
            } else {
                session.setAttribute(IS_ADMIN, false);
                dispatcher = req.getRequestDispatcher(MAIN_JSP);
                dispatcher.forward(req, res);
            }
        } else {
            req.setAttribute(WRONG_DATA, WRONG_LOGIN_OR_PASS_MSG);
            dispatcher = req.getRequestDispatcher(LOGIN_JSP);
            dispatcher.forward(req, res);
        }
    }
}
