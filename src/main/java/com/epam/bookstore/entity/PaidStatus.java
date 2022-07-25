package com.epam.bookstore.entity;

import java.util.Objects;

public class PaidStatus {

    private Long id;
    private String name;
    private Integer languageId;

    public PaidStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        PaidStatus that = (PaidStatus) o;

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(languageId, that.languageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, languageId);
    }

    @Override
    public String toString() {
        return "PaidStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", languageId=" + languageId +
                '}';
    }
}
