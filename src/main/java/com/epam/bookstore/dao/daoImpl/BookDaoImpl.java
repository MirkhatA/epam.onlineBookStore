package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.BookDao;
import com.epam.bookstore.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private static final String GET_ALL_BOOKS_BY_LANG_ID = "SELECT b.id, b.title, b.description, b.image, b.quantity, " +
            "b.price, a.full_name as author_name, p.name as publisher_name, b.language_id, g.name as genre_name FROM books b " +
            "INNER JOIN authors a ON a.id=b.author_id INNER JOIN publishers p ON p.id=b.publisher_id " +
            "INNER JOIN genres g ON g.id=b.genre_id WHERE b.language_id=? AND a.language_id=? AND g.language_id=?;";
    private static final String GET_BOOK_BY_ID = "SELECT b.id, b.title, b.description, b.image, b.quantity, " +
            "b.price, b.language_id, a.full_name as author_name, p.name as publisher_name, g.name as genre_name FROM books b " +
            "INNER JOIN authors a ON a.id=b.author_id INNER JOIN publishers p ON p.id=b.publisher_id INNER JOIN genres g " +
            "ON g.id=b.genre_id WHERE b.language_id=? AND a.language_id=? AND g.language_id=? AND b.id=?;";
    private static final String GET_ALL_BOOKS_BY_GENRE_ID = "SELECT b.id, b.title, b.description, b.image, b.quantity, " +
            "b.price, b.language_id, a.full_name as author_name, p.name as publisher_name, g.name as genre_name FROM books b " +
            "INNER JOIN authors a ON a.id=b.author_id INNER JOIN publishers p ON p.id=b.publisher_id INNER JOIN genres g " +
            "ON g.id=b.genre_id WHERE b.language_id=? AND a.language_id=? AND g.language_id=? AND b.genre_id=?;";
    private static final String GET_ALL_BOOKS_BY_AUTHOR_ID = "SELECT b.id, b.title, b.description, b.image, b.quantity, " +
            "b.price, b.language_id, a.full_name as author_name, p.name as publisher_name, g.name as genre_name FROM books b " +
            "INNER JOIN authors a ON a.id=b.author_id INNER JOIN publishers p ON p.id=b.publisher_id INNER JOIN genres g " +
            "ON g.id=b.genre_id WHERE b.language_id=? AND a.language_id=? AND g.language_id=? AND b.author_id=?;";

    ConnectionPool connectionPool;
    Connection connection;

    @Override
    public List<Book> getAll(int languageId) throws SQLException {
        List<Book> books = new ArrayList<>();

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_BOOKS_BY_LANG_ID)) {
            ps.setInt(1, languageId);
            ps.setInt(2, languageId);
            ps.setInt(3, languageId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                setBookData(book, rs);
                books.add(book);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return books;
    }

    @Override
    public Book getById(Long id, int languageId) throws SQLException {
        Book book = new Book();

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_BOOK_BY_ID)){
            ps.setInt(1, languageId);
            ps.setInt(2, languageId);
            ps.setInt(3, languageId);
            ps.setLong(4, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                setBookData(book, rs);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return book;
    }

    @Override
    public List<Book> getBooksByGenreId(Long genreId, int languageId) throws SQLException {
        List<Book> books = new ArrayList<>();

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_BOOKS_BY_GENRE_ID)) {
            ps.setInt(1, languageId);
            ps.setInt(2, languageId);
            ps.setInt(3, languageId);
            ps.setLong(4, genreId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                setBookData(book, rs);
                books.add(book);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return books;
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorId, int languageId) throws SQLException {
        List<Book> books = new ArrayList<>();

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_BOOKS_BY_AUTHOR_ID)) {
            ps.setInt(1, languageId);
            ps.setInt(2, languageId);
            ps.setInt(3, languageId);
            ps.setLong(4, authorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                setBookData(book, rs);
                books.add(book);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return books;
    }

    private void setBookData(Book book, ResultSet rs) throws SQLException {
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setDescription(rs.getString("description"));
        book.setImage(rs.getString("image"));
        book.setQuantity(rs.getInt("quantity"));
        book.setPrice(rs.getDouble("price"));
        book.setAuthorName(rs.getString("author_name"));
        book.setPublisherName(rs.getString("publisher_name"));
        book.setLanguageId(rs.getInt("language_id"));
        book.setGenre(rs.getString("genre_name"));
    }
}
