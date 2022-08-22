package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.PublisherDao;
import com.epam.bookstore.entity.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDaoImpl implements PublisherDao {

    private final String GET_ALL_PUBLISHERS = "select * from publishers";

    ConnectionPool connectionPool;
    Connection connection;

    @Override
    public List<Publisher> getAll() throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<Publisher> publishers = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_PUBLISHERS)){
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(rs.getLong("id"));
                publisher.setName(rs.getString("name"));
                publishers.add(publisher);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return publishers;
    }
}
