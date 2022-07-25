package com.epam.bookstore.entity;

import java.util.Objects;

public class Cart {

    private Long bookId;
    private String title;
    private Double price;
    private Integer quantity;
    private Long userId;
    private Double totalPrice;
    private Integer languageId;

    public Cart() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return Objects.equals(bookId, cart.bookId) &&
                Objects.equals(title, cart.title) &&
                Objects.equals(price, cart.price) &&
                Objects.equals(quantity, cart.quantity) &&
                Objects.equals(userId, cart.userId) &&
                Objects.equals(totalPrice, cart.totalPrice) &&
                Objects.equals(languageId, cart.languageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, price, quantity, userId, totalPrice, languageId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", languageId=" + languageId +
                '}';
    }
}
