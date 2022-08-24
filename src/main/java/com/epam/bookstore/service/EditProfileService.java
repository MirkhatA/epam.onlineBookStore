package com.epam.bookstore.service;

import com.epam.bookstore.dao.UserDao;
import com.epam.bookstore.dao.daoImpl.UserDaoImpl;
import com.epam.bookstore.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Objects;

import static com.epam.bookstore.constants.Constants.*;
import static com.epam.bookstore.constants.MessageConstants.*;
import static com.epam.bookstore.constants.PageNameConstants.*;

public class EditProfileService implements Service {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        String email = (String) session.getAttribute(EMAIL);
        String mobile = (String) session.getAttribute(MOBILE);

        User user = new User();
        setUserData(user, req);


        if (userDao.isEmailIsTaken(req.getParameter(EMAIL)) &&
                !Objects.equals(req.getParameter(EMAIL), email)) {
            req.setAttribute(EMAIL_IS_TAKEN, EMAIL_IS_TAKEN_MSG);
            dispatcher = req.getRequestDispatcher(PROFILE_JSP);
            dispatcher.forward(req, res);
        } else if (userDao.isMobileTaken(req.getParameter(MOBILE)) &&
                !Objects.equals(req.getParameter(MOBILE), mobile)) {
            req.setAttribute(MOBILE_IS_TAKEN, PHONE_IS_TAKEN_MSG);
            dispatcher = req.getRequestDispatcher(PROFILE_JSP);
            dispatcher.forward(req, res);
        } else if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getEmail().isEmpty() ||
                user.getAddress().isEmpty() || user.getMobile().isEmpty()) {
            req.setAttribute(EMPTY_FIELDS, FILL_ALL_DATA_MSG);
        } else {
            Long id = (Long) session.getAttribute(USER_ID);
            userDao.update(id, user);

            session.setAttribute(FIRST_NAME, user.getFirstName());
            session.setAttribute(LAST_NAME, user.getLastName());
            session.setAttribute(EMAIL, user.getEmail());
            session.setAttribute(ADDRESS, user.getAddress());
            session.setAttribute(MOBILE, user.getMobile());

            req.setAttribute(EDIT_PROFILE_SUCCESS, PROFILE_UPDATE_SUCCESS_MSG);
        }
        dispatcher = req.getRequestDispatcher(PROFILE_JSP);
        dispatcher.forward(req, res);
    }

    private void setUserData(User user, HttpServletRequest req) {
        user.setFirstName(req.getParameter(FIRST_NAME));
        user.setLastName(req.getParameter(LAST_NAME));
        user.setEmail(req.getParameter(EMAIL));
        user.setAddress(req.getParameter(ADDRESS));
        user.setMobile(req.getParameter(MOBILE));
    }
}
