package com.epam.bookstore.dao;

import com.epam.bookstore.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao extends Dao<Book> {
    List<Book> getBooksByGenreId(Long genreId, int languageId) throws SQLException;

    List<Book> getBooksByAuthorId(Long authorId, int languageId) throws SQLException;
}
