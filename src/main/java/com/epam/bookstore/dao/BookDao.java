package com.epam.bookstore.dao;

import com.epam.bookstore.entity.Book;
import com.epam.bookstore.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    List<Book> getBooksByGenreId(Long genreId, int languageId) throws SQLException;

    List<Book> getBooksByAuthorId(Long authorId, int languageId) throws SQLException;

    List<Book> getAll(int languageId) throws SQLException;

    Book getById(Long id, int languageId) throws SQLException;

    void create(List<Book> bookParams) throws SQLException;

    void update(List<Book> bookParams, Long id) throws SQLException;

    Long getLastId() throws SQLException;

    List<Book> getAllByStatus(int languageId) throws SQLException;

    void delete(Long id) throws SQLException;
}
