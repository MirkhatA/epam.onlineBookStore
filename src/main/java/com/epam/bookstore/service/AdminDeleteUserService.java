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
import java.util.List;

import static com.epam.bookstore.constants.PageNameConstants.*;

public class AdminDeleteUserService implements Service {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        Integer languageId = (Integer) session.getAttribute("languageId");

        Long id = Long.valueOf(req.getParameter("id"));

        if (isAdmin != null && isAdmin.equals(true)) {
            if (userDao.getOrdersNumber(id) > 0) {
                session.setAttribute("hasOrders", true);
                dispatcher = req.getRequestDispatcher(allUsersJsp);
                dispatcher.forward(req, res);
            } else {
                userDao.deleteUser(id);

                List<User> users = userDao.getAll(languageId);
                session.setAttribute("users", users);
                dispatcher = req.getRequestDispatcher(allUsersJsp);
                dispatcher.forward(req, res);

            }
        } else {
            dispatcher = req.getRequestDispatcher(errorJsp);
            dispatcher.forward(req, res);
        }
    }
}
