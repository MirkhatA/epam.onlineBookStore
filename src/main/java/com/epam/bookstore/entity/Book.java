package com.epam.bookstore.entity;

import java.util.Objects;

public class Book {

    private Long id;
    private String title;
    private String description;
    private String image;
    private int quantity;
    private Double price;
    private Long authorId;
    private String authorName;
    private String publisherName;
    private Long publisherId;
    private Integer languageId;
    private Long genreId;
    private String genre;
    private String status;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return quantity == book.quantity && Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(description, book.description) && Objects.equals(image, book.image) && Objects.equals(price, book.price) && Objects.equals(authorId, book.authorId) && Objects.equals(authorName, book.authorName) && Objects.equals(publisherName, book.publisherName) && Objects.equals(publisherId, book.publisherId) && Objects.equals(languageId, book.languageId) && Objects.equals(genreId, book.genreId) && Objects.equals(genre, book.genre) && Objects.equals(status, book.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, image, quantity, price, authorId, authorName, publisherName, publisherId, languageId, genreId, genre, status);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", publisherId=" + publisherId +
                ", languageId=" + languageId +
                ", genreId=" + genreId +
                ", genre='" + genre + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
