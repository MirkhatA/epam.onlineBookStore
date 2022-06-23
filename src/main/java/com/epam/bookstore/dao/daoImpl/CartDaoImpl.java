package com.epam.bookstore.dao.daoImpl;

import com.epam.bookstore.connection.ConnectionPool;
import com.epam.bookstore.dao.CartDao;
import com.epam.bookstore.entity.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private static String GET_CART_BY_USER_ID = "SELECT book_id, b.title, b.price, (b.price * c.quantity) AS total_price, " +
            "c.quantity, user_id, b.language_id FROM cart c JOIN books b ON b.id = c.book_id WHERE user_id=? AND language_id=?;";
    private static String ADD_BOOK_TO_CART_BY_USER_ID = "INSERT INTO cart(user_id, book_id, quantity) VALUE(?,?,1);";
    private static String GET_BOOK_QUANTITY = "SELECT quantity FROM cart WHERE user_id=? AND book_id=?;";
    private static String INCREASE_BOOK_QUANTITY = "UPDATE cart SET quantity = quantity + 1 WHERE user_id=? AND book_id=?;";
    private static String DECREASE_BOOK_QUANTITY = "UPDATE cart SET quantity = quantity - 1 WHERE user_id=? AND book_id=?;";
    private static String DELETE_FROM_CART_BY_BOOK_ID = "DELETE FROM cart WHERE user_id=? AND book_id=?;";
    private static String GET_TOTAL_PRICE = "SELECT SUM(b.price * c.quantity) AS total_price FROM cart c " +
            "JOIN books b ON b.id = c.book_id WHERE user_id = ? AND language_id = ?;";

    ConnectionPool connectionPool;
    Connection connection;

    @Override
    public List<Cart> getCartByUserId(Long userId, Integer languageId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        List<Cart> cartList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_CART_BY_USER_ID)) {
            ps.setLong(1, userId);
            ps.setInt(2, languageId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                setCartData(cart, rs);
                cartList.add(cart);
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return cartList;
    }

    @Override
    public void increaseBookInCart(Long userId, Long bookId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(INCREASE_BOOK_QUANTITY)){
            ps.setLong(1, userId);
            ps.setLong(2, bookId);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void decreaseBookInCart(Long userId, Long bookId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(DECREASE_BOOK_QUANTITY)){
            ps.setLong(1, userId);
            ps.setLong(2, bookId);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public int getBookQuantityInCart(Long userId, Long bookId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        int quantity = 0;

        try (PreparedStatement ps = connection.prepareStatement(GET_BOOK_QUANTITY)){
            ps.setLong(1, userId);
            ps.setLong(2, bookId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return quantity;
    }

    @Override
    public void deleteBookFromCart(Long userId, Long bookId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(DELETE_FROM_CART_BY_BOOK_ID)){
            ps.setLong(1, userId);
            ps.setLong(2, bookId);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Double getTotalPriceFromCart(Long userId, Integer languageId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        Double totalPrice = 0d;

        try (PreparedStatement ps = connection.prepareStatement(GET_TOTAL_PRICE)){
            ps.setLong(1, userId);
            ps.setLong(2, languageId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalPrice = rs.getDouble("total_price");
            }
        } finally {
            connectionPool.returnConnection(connection);
        }

        return totalPrice;
    }

    @Override
    public void addBookToCart(Long userId, Long bookId) throws SQLException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();

        try (PreparedStatement ps = connection.prepareStatement(ADD_BOOK_TO_CART_BY_USER_ID)){
            ps.setLong(1, userId);
            ps.setLong(2, bookId);
            ps.executeUpdate();
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Cart> getAll(int languageId) throws SQLException {
        return null;
    }

    @Override
    public Cart getById(Long id, int languageId) throws SQLException {
        return null;
    }

    @Override
    public void create(Cart cart) throws SQLException {

    }

    @Override
    public void update(Long id, Cart cart) throws SQLException {

    }

    private void setCartData(Cart cart, ResultSet rs) throws SQLException {
        cart.setBookId(rs.getLong("book_id"));
        cart.setTitle(rs.getString("title"));
        cart.setPrice(rs.getDouble("price"));
        cart.setTotalPrice(rs.getDouble("total_price"));
        cart.setQuantity(rs.getInt("quantity"));
        cart.setUserId(rs.getLong("user_id"));
        cart.setLanguageId(rs.getInt("language_id"));
    }
}
