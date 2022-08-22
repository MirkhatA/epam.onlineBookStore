package com.epam.bookstore.dao;

import com.epam.bookstore.entity.Publisher;

import java.sql.SQLException;
import java.util.List;

public interface PublisherDao {

    List<Publisher> getAll() throws SQLException;

}
