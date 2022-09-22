package com.example.thicuoiky3.service;

import com.example.thicuoiky3.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProduct {

    public void insertProduct(Product product) throws SQLException;

    public Product selectProduct(Long id) throws SQLException;

    public List<Product> selectAllProduct(String search) throws SQLException;

    public void deleteProduct(Long id) throws SQLException;

    public void updateProduct(Long id, Product product) throws SQLException;
}
