package com.epam.bookstore.service;

import com.epam.bookstore.dao.UserDao;
import com.epam.bookstore.dao.daoImpl.UserDaoImpl;
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
import static com.epam.bookstore.constants.MessageConstants.*;
import static com.epam.bookstore.constants.PageNameConstants.*;

public class EditPasswordService implements Service {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute(USER_ID);

        String oldPassword = userDao.getUserPassword(userId);

        if (!req.getParameter(OLD_PASSWORD).equals(req.getParameter(PASSWORD_REPEAT))) {
            req.setAttribute(PASSWORDS_ARE_DIFFERENT, SAME_PASSWORDS_MSG);
            dispatcher = req.getRequestDispatcher(CHANGE_PASSWORD_JSP);
            dispatcher.forward(req, res);
        } else if (req.getParameter(OLD_PASSWORD).isEmpty() || req.getParameter(NEW_PASSWORD).isEmpty() ||
                req.getParameter(PASSWORD_REPEAT).isEmpty()) {
            req.setAttribute(EMPTY_FIELDS, FILL_ALL_DATA_MSG);
            dispatcher = req.getRequestDispatcher(CHANGE_PASSWORD_JSP);
            dispatcher.forward(req, res);
        } else if (!oldPassword.equals(DigestUtils.md5Hex(req.getParameter(OLD_PASSWORD)))) {
            req.setAttribute(OLD_PASSWORD_IS_INCORRECT, OLD_PASSWORD_INCORRECT_MSG);
            dispatcher = req.getRequestDispatcher(CHANGE_PASSWORD_JSP);
            dispatcher.forward(req, res);
        } else {
            String newPassword = DigestUtils.md5Hex(req.getParameter(NEW_PASSWORD));
            userDao.updatePassword(userId, newPassword);
            req.setAttribute(EDIT_PASSWORD_SUCCESS, PASSWORD_UPDATED_SUCCESS_MSG);
            dispatcher = req.getRequestDispatcher(CHANGE_PASSWORD_JSP);
            dispatcher.forward(req, res);
        }
    }
}
