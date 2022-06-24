package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.PaidStatusDao;
import com.epam.bookstore.entity.PaidStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PaidStatusDaoImpl implements PaidStatusDao {
    private final String GET_ALL_ITEMS = "SELECT * FROM paid_status WHERE language_id=?";

    ConnectionPool connectionPool;
    Connection connection;

    @Override
    public List<PaidStatus> getAll(int languageId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<PaidStatus> paidStatusList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ITEMS)){
            ps.setInt(1, languageId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PaidStatus paidStatus = new PaidStatus();
                paidStatus.setId(rs.getLong("id"));
                paidStatus.setName(rs.getString("name"));
                paidStatus.setLanguageId(rs.getInt("language_id"));
                paidStatusList.add(paidStatus);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return paidStatusList;
    }

    @Override
    public PaidStatus getById(Long id, int languageId) throws SQLException {
        return null;
    }

    @Override
    public void create(PaidStatus paidStatus) throws SQLException {

    }

    @Override
    public void update(Long id, PaidStatus paidStatus) throws SQLException {

    }

}
