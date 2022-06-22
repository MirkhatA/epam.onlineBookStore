package com.epam.bookstore.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public interface Service {
    void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException,
            ParseException, SQLException;
}
