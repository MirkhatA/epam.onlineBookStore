package com.epam.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    List<T> getAll(int languageId) throws SQLException;

    T getById(Long id, int languageId) throws SQLException;

    void create(T t) throws SQLException;

    void update(Long id, T t) throws SQLException;
}
