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

import static com.epam.bookstore.constants.PageNameConstants.loginJsp;
import static com.epam.bookstore.constants.PageNameConstants.registrationJsp;

public class RegistrationService implements Service{
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;

        if (!req.getParameter("password").equals(req.getParameter("rePassword"))) {
            req.setAttribute("passwordsAreDifferent", "These passwords are different");
            dispatcher = req.getRequestDispatcher(registrationJsp);
            dispatcher.forward(req, res);
        } else if (userDao.isEmailIsTaken(req.getParameter("email"))) {
            req.setAttribute("emailIsTaken", "This email is already taken");
            dispatcher = req.getRequestDispatcher(registrationJsp);
            dispatcher.forward(req, res);
        } else if (userDao.isMobileTaken(req.getParameter("mobile"))) {
            req.setAttribute("mobileIsTaken", "This mobile is already taken");
            dispatcher = req.getRequestDispatcher(registrationJsp);
            dispatcher.forward(req, res);
        } else {
            User user = new User();
            setUserData(user, req);
            if (user.getFirstName().isEmpty() || user.getMobile().isEmpty() || user.getEmail().isEmpty() ||
                    user.getPassword().isEmpty()) {
                req.setAttribute("emptyFields", "Please fill all data");
                dispatcher = req.getRequestDispatcher(registrationJsp);
                dispatcher.forward(req, res);
            } else {
                userDao.create(user);
                req.setAttribute("registerSuccess", "You have successfully registered!");
                dispatcher = req.getRequestDispatcher(loginJsp);
                dispatcher.forward(req, res);
            }
        }
    }

    private void setUserData(User user, HttpServletRequest req) {
        user.setFirstName(req.getParameter("firstName"));
        user.setMobile(req.getParameter("mobile"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
    }
}
