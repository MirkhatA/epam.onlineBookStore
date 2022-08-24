package com.epam.bookstore.dao;

import com.epam.bookstore.entity.Book;
import com.epam.bookstore.entity.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDao {
    List<Genre> getAll(int languageId) throws SQLException;

    void create(List<String> genreParams) throws SQLException;

    Long getLastId() throws SQLException;

    void delete(Long genreId) throws SQLException;

    void update(List<String> genreParams, Long id) throws SQLException;

    List<String> getById(Long genreId) throws SQLException;
}
