package com.epam.bookstore.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.bookstore.constants.PageNameConstants.errorJsp;

public class ErrorService implements Service{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, ParseException, SQLException {
        res.sendRedirect(errorJsp);
    }
}
