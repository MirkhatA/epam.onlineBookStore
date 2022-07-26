package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.UserDao;
import com.epam.bookstore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String INSERT_USER = "INSERT INTO users(first_name, last_name, email, password, address, mobile, " +
            "registered_at, status, role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String GET_ROLE_NAME = "SELECT * FROM role WHERE id=?;";
    private static final String GET_ID_BY_EMAIL = "SELECT id FROM users WHERE email=?;";
    private static final String GET_ID_BY_PHONE = "SELECT id FROM users WHERE mobile=?;";
    private static final String GET_USER_BY_LOGIN_PASSWORD = "SELECT * FROM users WHERE (email=? OR mobile=?) AND " +
            "password=?;";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET first_name=?, last_name=?, email=?, address=?, " +
            "mobile=? WHERE id=?;";
    private static final String GET_USER_PASSWORD_BY_ID = "SELECT password FROM users WHERE id=?;";
    private static final String UPDATE_USER_PASSWORD_BY_ID = "UPDATE users SET password=? WHERE id=?;";
    private static final String GET_ALL_USERS = "SELECT * FROM users WHERE role_id=1";
    private static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
    private static final String COUNT_TOTAL_ORDERS = "SELECT COUNT(*) AS total FROM `order` WHERE user_id=?;";
    private static final String BLOCK_USER_BY_ID = "UPDATE users SET status='Inactive' WHERE id=?;";
    private static final String UNBLOCK_USER_BY_ID = "UPDATE users SET status='Active' WHERE id=?;";

    private ConnectionPool connectionPool;
    private Connection connection;

    @Override
    public List<User> getAll(int languageId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS)){
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                setUserData(user, rs);
                users.add(user);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return users;
    }

    @Override
    public void create(User user) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Date date = Date.valueOf(java.time.LocalDate.now());

        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)){
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getMobile());
            ps.setDate(7, date);
            ps.setString(8, "Active");
            ps.setInt(9, 1);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void update(Long id, User user) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getMobile());
            ps.setLong(6, id);

            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void blockUser(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(BLOCK_USER_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void unblockUser(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(UNBLOCK_USER_BY_ID)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public boolean isMobileTaken(String mobile) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        boolean isExist = false;

        try (PreparedStatement ps = connection.prepareStatement(GET_ID_BY_PHONE)) {
            ps.setString(1, mobile);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                isExist = true;
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return isExist;
    }

    @Override
    public boolean isEmailIsTaken(String email) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        boolean isExist = false;

        try (PreparedStatement ps = connection.prepareStatement(GET_ID_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                isExist = true;
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return isExist;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        User user = null;

        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_LOGIN_PASSWORD)){
            ps.setString(1, login);
            ps.setString(2, login);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                setUserData(user, rs);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return user;
    }

    @Override
    public void updatePassword(Long id, String password) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_ID)){
            ps.setString(1, password);
            ps.setLong(2, id);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }

    }

    @Override
    public String getUserPassword(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        String password = "";

        try (PreparedStatement ps = connection.prepareStatement(GET_USER_PASSWORD_BY_ID)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                password = rs.getString("password");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return password;
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID)){
            ps.setLong(1, id);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Integer getOrdersNumber(Long userId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        int total = 0;

        try(PreparedStatement ps = connection.prepareStatement(COUNT_TOTAL_ORDERS)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getInt("total");
            }
        }

        return total;
    }

    private void setUserData(User user, ResultSet rs) throws SQLException {
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setAddress(rs.getString("address"));
        user.setMobile(rs.getString("mobile"));
        user.setRegisteredAt(rs.getDate("registered_at"));
        user.setStatus(rs.getString("status"));
        user.setRoleId(rs.getInt("role_id"));
    }
}
