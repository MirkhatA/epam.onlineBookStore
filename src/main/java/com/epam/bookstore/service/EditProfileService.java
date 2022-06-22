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

import static com.epam.bookstore.constants.PageNameConstants.*;

public class EditProfileService implements Service {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        RequestDispatcher dispatcher;
        HttpSession session = req.getSession();

        String email = (String) session.getAttribute("email");
        String mobile = (String) session.getAttribute("mobile");

        User user = new User();
        setUserData(user, req);


        if (userDao.isEmailIsTaken(req.getParameter("email")) &&
                !Objects.equals(req.getParameter("email"), email)) {
            req.setAttribute("emailIsTaken", "This email is already taken");
            dispatcher = req.getRequestDispatcher(profileJsp);
            dispatcher.forward(req, res);
        } else if (userDao.isMobileTaken(req.getParameter("mobile")) &&
                !Objects.equals(req.getParameter("mobile"), mobile)) {
            req.setAttribute("mobileIsTaken", "This mobile is already taken");
            dispatcher = req.getRequestDispatcher(profileJsp);
            dispatcher.forward(req, res);
        } else if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getEmail().isEmpty() ||
                user.getAddress().isEmpty() || user.getMobile().isEmpty()) {
            req.setAttribute("emptyFields", "Please fill all data");
        } else {
            Long id = (Long) session.getAttribute("userId");
            userDao.update(id, user);

            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("lastName", user.getLastName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("address", user.getAddress());
            session.setAttribute("mobile", user.getMobile());

            req.setAttribute("editProfileSuccess", "You have successfully updated your profile data!");
        }
        dispatcher = req.getRequestDispatcher(profileJsp);
        dispatcher.forward(req, res);
    }

    private void setUserData(User user, HttpServletRequest req) {
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));
        user.setAddress(req.getParameter("address"));
        user.setMobile(req.getParameter("mobile"));
    }
}
