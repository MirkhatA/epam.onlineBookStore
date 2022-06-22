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

import static com.epam.bookstore.constants.PageNameConstants.*;

public class LoginService implements Service {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = userDao.getUserByLoginAndPassword(login, password);

        if (user != null) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("lastName", user.getLastName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("address", user.getAddress());
            session.setAttribute("mobile", user.getMobile());
            session.setAttribute("registeredDate", user.getRegisteredAt());
            session.setAttribute("roleId", user.getRoleId());

            dispatcher = req.getRequestDispatcher(mainJsp);
            dispatcher.forward(req, res);
        } else {
            req.setAttribute("wrongData", "Wrong data");
            dispatcher = req.getRequestDispatcher(loginJsp);
            dispatcher.forward(req, res);
        }
    }
}
