package com.example.thicuoiky3.service;

import com.example.thicuoiky3.model.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProduct{
    private String jdbcURL = "jdbc:mysql://localhost:3306/thi_cuoi_ky?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "241298";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO `thi_cuoi_ky`.`product` (`name`, `price`, `quantity`, `color`, `info`, `category`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE idproduct = ?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product WHERE name LIKE ?";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM `thi_cuoi_ky`.`product` WHERE (`idproduct` = ?)";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE `thi_cuoi_ky`.`product` SET `name` = ?, `price` = ?, `quantity` = ?, `color` = ?, `info` = ?, `category` = ? WHERE (`idproduct` = ?)";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }



    @Override
    public void insertProduct(Product product) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setBigDecimal(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getColor());
        preparedStatement.setString(5, product.getInfo());
        preparedStatement.setLong(6, product.getIdCategory());

        System.out.println(this.getClass() + " insertProduct() : " + preparedStatement);

        preparedStatement.execute();
    }

    @Override
    public Product selectProduct(Long id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
        preparedStatement.setLong(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        System.out.println(this.getClass() + " selectProduct() : " + preparedStatement);

        while (rs.next()) {
            Long idProduct = rs.getLong("idproduct");
            String name = rs.getString("name");
            BigDecimal price = rs.getBigDecimal("price");
            int quantity = rs.getInt("quantity");
            String color = rs.getString("color");
            String info = rs.getString("info");
            Long idCategory = Long.parseLong(rs.getString("category"));

            Product product = new Product(idProduct, name, price, quantity, color, info, idCategory);

            return product;
        }
        return null;
    }

    @Override
    public List<Product> selectAllProduct(String search) throws SQLException {
        List<Product> products = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
        preparedStatement.setString(1, "%" + search + "%");

//        Statement statement = connection.createStatement();

        ResultSet rs = preparedStatement.executeQuery();
        System.out.println(this.getClass() + " selectAllProduct() : " + preparedStatement);

        while (rs.next()) {
            Long idProduct = rs.getLong("idproduct");
            String name = rs.getString("name");
            BigDecimal price = rs.getBigDecimal("price");
            int quantity = rs.getInt("quantity");
            String color = rs.getString("color");
            String info = rs.getString("info");
            Long idCategory = Long.parseLong(rs.getString("category"));


            Product product = new Product(idProduct, name, price, quantity, color, info, idCategory);
            products.add(product);
        }

        return products;
    }

    @Override
    public void deleteProduct(Long id) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL);
        preparedStatement.setLong(1, id);

        System.out.println(this.getClass() + " deleteProduct() : " + preparedStatement);
        preparedStatement.execute();

        String message = "successfully deleted";
        System.out.println("delete......: " + message);
    }

    @Override
    public void updateProduct(Long id, Product product) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setBigDecimal(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getColor());
        preparedStatement.setString(5, product.getInfo());
        preparedStatement.setLong(6, product.getIdCategory());
        preparedStatement.setLong(7, id);

        System.out.println(this.getClass() + " updateProduct() : " + preparedStatement);

        preparedStatement.execute();
    }
}
