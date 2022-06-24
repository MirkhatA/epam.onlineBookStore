package com.epam.bookstore.entity;

import java.util.Date;

public class Order {

    private Long id;
    private Long userId;
    private String fullName;
    private Double totalPrice;
    private String address;
    private Date createdAt;
    private Integer statusId;
    private String status;
    private Integer isPaidId;
    private String isPaid;
    private String mobile;
    private String comment;
    private String paymentWay;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsPaidId() {
        return isPaidId;
    }

    public void setIsPaidId(Integer isPaidId) {
        this.isPaidId = isPaidId;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", totalPrice=" + totalPrice +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                ", statusId=" + statusId +
                ", status='" + status + '\'' +
                ", isPaidId=" + isPaidId +
                ", isPaid='" + isPaid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", comment='" + comment + '\'' +
                ", paymentWay='" + paymentWay + '\'' +
                '}';
    }
}
