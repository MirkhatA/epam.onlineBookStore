package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.BookDao;
import com.epam.bookstore.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.epam.bookstore.constants.Constants.ENGLISH_LANGUAGE_ID;
import static com.epam.bookstore.constants.Constants.RUSSIAN_LANGUAGE_ID;

public class BookDaoImpl implements BookDao {
    private static final String GET_ALL_BOOKS_BY_LANG_ID = "SELECT b.id, b.title, b.description, b.image, b.quantity, b.author_id, b.publisher_id, b.genre_id," +
            "b.price, a.full_name as author_name, p.name as publisher_name, b.language_id, g.name as genre_name FROM books b " +
            "INNER JOIN authors a ON a.id=b.author_id INNER JOIN publishers p ON p.id=b.publisher_id " +
            "INNER JOIN genres g ON g.id=b.genre_id WHERE b.language_id=? AND a.language_id=? AND g.language_id=? ORDER BY b.id;";
    private static final String GET_BOOK_BY_ID = "SELECT b.id, b.title, b.description, b.image, b.quantity, b.author_id, b.publisher_id, b.genre_id," +
            "b.price, b.language_id, a.full_name as author_name, p.name as publisher_name, g.name as genre_name FROM books b " +
            "INNER JOIN authors a ON a.id=b.author_id INNER JOIN publishers p ON p.id=b.publisher_id INNER JOIN genres g " +
            "ON g.id=b.genre_id WHERE b.language_id=? AND a.language_id=? AND g.language_id=? AND b.id=?;";
    private static final String GET_ALL_BOOKS_BY_GENRE_ID = "SELECT b.id, b.title, b.description, b.image, b.quantity, b.author_id, b.publisher_id, b.genre_id," +
            "b.price, b.language_id, a.full_name as author_name, p.name as publisher_name, g.name as genre_name FROM books b " +
            "INNER JOIN authors a ON a.id=b.author_id INNER JOIN publishers p ON p.id=b.publisher_id INNER JOIN genres g " +
            "ON g.id=b.genre_id WHERE b.language_id=? AND a.language_id=? AND g.language_id=? AND b.genre_id=?;";
    private static final String GET_ALL_BOOKS_BY_AUTHOR_ID = "SELECT b.id, b.title, b.description, b.image, b.quantity, b.author_id, b.publisher_id, b.genre_id," +
            "b.price, b.language_id, a.full_name as author_name, p.name as publisher_name, g.name as genre_name FROM books b " +
            "INNER JOIN authors a ON a.id=b.author_id INNER JOIN publishers p ON p.id=b.publisher_id INNER JOIN genres g " +
            "ON g.id=b.genre_id WHERE b.language_id=? AND a.language_id=? AND g.language_id=? AND b.author_id=?;";
    private static final String UPDATE_BOOK_BY_ID_AND_LANGUAGE_ID= "UPDATE books SET title=?, description=?, image=?, " +
            "quantity=?, price=?, author_id=?, publisher_id=?, genre_id=? WHERE id=? AND language_id=?;";
    private static final String ADD_BOOK = "INSERT INTO books VALUE (?, ?, ?, ?, ?, ?, ?, ?, 1, ?), (?, ?, ?, ?, ?, ?, ?, ?, 2, ?);";
    private static final String GET_LAST_ID = "SELECT id FROM books ORDER BY id DESC LIMIT 1;";
    private static final String DELETE_BOOK_BY_ID = "DELETE FROM books WHERE id=?;";

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
    public void create(List<Book> bookParams) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Book bookEng = bookParams.get(ENGLISH_LANGUAGE_ID - 1);
        Book bookRu = bookParams.get(RUSSIAN_LANGUAGE_ID - 1);

        // get next id of last book
        Long lastId = getLastId() + 1;

        try (PreparedStatement ps = connection.prepareStatement(ADD_BOOK)){
            ps.setLong(1, lastId);
            ps.setString(2, bookEng.getTitle());
            ps.setString(3, bookEng.getDescription());
            ps.setString(4, bookEng.getImage());
            ps.setInt(5, bookEng.getQuantity());
            ps.setDouble(6, bookEng.getPrice());
            ps.setLong(7, bookEng.getAuthorId());
            ps.setLong(8, bookEng.getPublisherId());
            ps.setLong(9, bookEng.getGenreId());

            ps.setLong(10, lastId);
            ps.setString(11, bookRu.getTitle());
            ps.setString(12, bookRu.getDescription());
            ps.setString(13, bookRu.getImage());
            ps.setInt(14, bookRu.getQuantity());
            ps.setDouble(15, bookRu.getPrice());
            ps.setLong(16, bookRu.getAuthorId());
            ps.setLong(17, bookRu.getPublisherId());
            ps.setLong(18, bookRu.getGenreId());
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(List<Book> bookParams, Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK_BY_ID_AND_LANGUAGE_ID)) {

            for (Book book : bookParams) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getDescription());
                ps.setString(3, book.getImage());
                ps.setInt(4, book.getQuantity());
                ps.setDouble(5, book.getPrice());
                ps.setLong(6, book.getAuthorId());
                ps.setLong(7, book.getPublisherId());
                ps.setLong(8, book.getGenreId());
                ps.setLong(9, id);
                ps.setLong(10, book.getLanguageId());
                ps.executeUpdate();
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Long getLastId() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Long lastId = 0L;

        try (PreparedStatement ps = connection.prepareStatement(GET_LAST_ID)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lastId = rs.getLong("id");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return lastId;
    }

    @Override
    public void delete(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(DELETE_BOOK_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
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
        book.setAuthorId(rs.getLong("author_id"));
        book.setAuthorName(rs.getString("author_name"));
        book.setPublisherId(rs.getLong("publisher_id"));
        book.setPublisherName(rs.getString("publisher_name"));
        book.setLanguageId(rs.getInt("language_id"));
        book.setGenreId(rs.getLong("genre_id"));
        book.setGenre(rs.getString("genre_name"));
    }
}
