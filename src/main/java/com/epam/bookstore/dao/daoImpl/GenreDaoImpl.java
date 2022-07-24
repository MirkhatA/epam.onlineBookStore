package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.GenreDao;
import com.epam.bookstore.entity.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements GenreDao {

    private static final String GET_ALL_GENRES_BY_LANG_ID = "SELECT * FROM genres WHERE language_id=?;";

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<Genre> getAll(int languageId) throws SQLException {
        List<Genre> genres = new ArrayList<>();

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_GENRES_BY_LANG_ID)){
            ps.setInt(1, languageId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                setGenreData(genres, rs);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return genres;
    }

    private void setGenreData(List<Genre> genres, ResultSet rs) throws SQLException {
        Genre genre = new Genre();

        genre.setId(rs.getLong("id"));
        genre.setName(rs.getString("name"));
        genre.setLanguageId(rs.getInt("language_id"));

        genres.add(genre);
    }
}
