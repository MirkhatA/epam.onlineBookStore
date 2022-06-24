package com.epam.bookstore.dao;

import com.epam.bookstore.entity.User;

import java.sql.SQLException;

public interface UserDao extends Dao<User> {
    boolean isMobileTaken(String mobile) throws SQLException;

    boolean isEmailIsTaken(String email) throws SQLException;

    User getUserByLoginAndPassword(String login, String password) throws SQLException;

    void updatePassword(Long id, String password) throws SQLException;

    String getUserPassword(Long id) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    Integer getOrdersNumber(Long userId) throws SQLException;
}
