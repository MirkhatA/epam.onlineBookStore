package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.AuthorDao;
import com.epam.bookstore.entity.Author;
import com.epam.bookstore.entity.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private static final String GET_ALL_AUTHORS_BY_LANG_ID = "SELECT * FROM authors WHERE language_id=?;";

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<Author> getAll(int languageId) throws SQLException {
        List<Author> authors = new ArrayList<>();

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_AUTHORS_BY_LANG_ID)){
            ps.setInt(1, languageId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                setGenreData(authors, rs);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return authors;
    }

    @Override
    public Author getById(Long id, int languageId) {
        return null;
    }

    @Override
    public void create(Author author) throws SQLException {

    }

    @Override
    public void update(Long id, Author author) throws SQLException {

    }


    private void setGenreData(List<Author> authors, ResultSet rs) throws SQLException {
        Author author = new Author();

        author.setId(rs.getLong("id"));
        author.setFullName(rs.getString("full_name"));
        author.setLanguageId(rs.getInt("language_id"));

        authors.add(author);
    }
}
