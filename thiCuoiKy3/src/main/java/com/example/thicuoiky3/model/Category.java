package com.example.thicuoiky3.model;

public class Category {
    private Long idCategory;
    private String category;

    public Category() {}

    public Category(Long idCategory, String category) {
        this.idCategory = idCategory;
        this.category = category;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
