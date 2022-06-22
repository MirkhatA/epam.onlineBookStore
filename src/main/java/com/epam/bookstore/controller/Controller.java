package com.epam.bookstore.controller;

import com.epam.bookstore.service.FactoryService;
import com.epam.bookstore.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Controller extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = req.getRequestURI();
        FactoryService serviceFactory = FactoryService.getInstance();
        Service service = serviceFactory.getService(request);

        try {
            service.execute(req, resp);
        } catch (ParseException | SQLException e) {
            logger.error(e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
