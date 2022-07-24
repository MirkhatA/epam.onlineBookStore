package com.epam.bookstore.dao;

import com.epam.bookstore.entity.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDao {

    List<Author> getAll(int languageId) throws SQLException;

}
