package com.example.thicuoiky3.service;

import com.example.thicuoiky3.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategory {
    public List<Category> selectAllCategory();

    public Category selectIdType(Long idCategory) throws SQLException;
}
