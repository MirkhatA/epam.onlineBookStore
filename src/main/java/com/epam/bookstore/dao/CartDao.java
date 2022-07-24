package com.epam.bookstore.dao;

import com.epam.bookstore.entity.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    List<Cart> getCartByUserId(Long userId, Integer languageId) throws SQLException;

    void addBookToCart(Long userId, Long bookId) throws SQLException;

    void increaseBookInCart(Long userId, Long bookId) throws SQLException;

    void decreaseBookInCart(Long userId, Long bookId) throws SQLException;

    int getBookQuantityInCart(Long userId, Long bookId) throws SQLException;

    void deleteBookFromCart(Long userId, Long bookId) throws SQLException;

    Double getTotalPriceFromCart(Long userId, Integer languageId) throws SQLException;

    void deleteAllFromCart(Long userId) throws SQLException;
}
