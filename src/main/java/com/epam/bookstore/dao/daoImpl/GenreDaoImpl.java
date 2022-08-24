package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.GenreDao;
import com.epam.bookstore.entity.Book;
import com.epam.bookstore.entity.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.bookstore.constants.Constants.ENGLISH_LANGUAGE_ID;
import static com.epam.bookstore.constants.Constants.RUSSIAN_LANGUAGE_ID;

public class GenreDaoImpl implements GenreDao {

    private static final String GET_ALL_GENRES_BY_LANG_ID = "SELECT * FROM genres WHERE language_id=?;";
    private static final String INSERT_GENRE = "INSERT INTO genres VALUE (?, ?, 1), (?, ?, 2);";
    private static final String GET_LAST_ID = "SELECT id FROM genres ORDER BY id DESC LIMIT 1;";
    private static final String DELETE_GENRE_BY_ID = "DELETE FROM genres WHERE id=?";
    private static final String GET_GENRE_BY_ID = "SELECT name FROM genres WHERE id=?;";
    private static final String UPDATE_GENRE_BY_ID_AND_LANGUAGE_ID= "UPDATE genres SET name=? WHERE id=? AND language_id=?;";

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

    @Override
    public void create(List<String> genreParams) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        String genreEn = genreParams.get(ENGLISH_LANGUAGE_ID - 1);
        String genreRu = genreParams.get(RUSSIAN_LANGUAGE_ID - 1);
        Long lastId = getLastId() + 1;

        try (PreparedStatement ps = connection.prepareStatement(INSERT_GENRE)){
            ps.setLong(1, lastId);
            ps.setString(2, genreEn);
            ps.setLong(3, lastId);
            ps.setString(4, genreRu);
            ps.executeUpdate();
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
    public void delete(Long genreId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_GENRE_BY_ID)) {
            ps.setLong(1, genreId);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(List<String> genreParams, Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        int langId = 1;

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_GENRE_BY_ID_AND_LANGUAGE_ID)) {
            for (String genreName : genreParams) {
                ps.setString(1, genreName);
                ps.setLong(2, id);
                ps.setLong(3, langId);
                ps.executeUpdate();
                System.out.println(ps.toString());
                langId+=1;
            }
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<String> getById(Long genreId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<String> genreParams = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_GENRE_BY_ID)) {
            ps.setLong(1, genreId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                genreParams.add(rs.getString("name"));
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return genreParams;
    }

    private void setGenreData(List<Genre> genres, ResultSet rs) throws SQLException {
        Genre genre = new Genre();

        genre.setId(rs.getLong("id"));
        genre.setName(rs.getString("name"));
        genre.setLanguageId(rs.getInt("language_id"));

        genres.add(genre);
    }
}
