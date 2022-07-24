package com.epam.bookstore.dao;

import com.epam.bookstore.entity.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDao {
    List<Genre> getAll(int languageId) throws SQLException;
}
