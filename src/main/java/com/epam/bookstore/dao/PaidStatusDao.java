package com.epam.bookstore.dao;

import com.epam.bookstore.entity.PaidStatus;

import java.sql.SQLException;
import java.util.List;

public interface PaidStatusDao {

    List<PaidStatus> getAll(int languageId) throws SQLException;

}
