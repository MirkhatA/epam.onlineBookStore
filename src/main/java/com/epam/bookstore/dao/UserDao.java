package com.epam.bookstore.dao;

import com.epam.bookstore.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    boolean isMobileTaken(String mobile) throws SQLException;

    boolean isEmailIsTaken(String email) throws SQLException;

    User getUserByLoginAndPassword(String login, String password) throws SQLException;

    void updatePassword(Long id, String password) throws SQLException;

    String getUserPassword(Long id) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    Integer getOrdersNumber(Long userId) throws SQLException;

    List<User> getAll(int languageId) throws SQLException;

    void create(User user) throws SQLException;

    void update(Long id, User user) throws SQLException;

}
