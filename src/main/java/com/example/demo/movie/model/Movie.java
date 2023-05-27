package com.example.demo.movie.model;

public class Movie {
    private Integer id;

    private String name;

    private Boolean isAvailable = false;

    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }
}
