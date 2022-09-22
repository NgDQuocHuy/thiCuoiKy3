package com.example.thicuoiky3.service;

import com.example.thicuoiky3.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartegoryService implements ICategory{
    private String jdbcURL = "jdbc:mysql://localhost:3306/thi_cuoi_ky?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "241298";

    private static final String SELECT_ALL_IDTYPE = "SELECT * FROM thi_cuoi_ky.category";
    private static final String TYPE_BY_ID = "SELECT * FROM thi_cuoi_ky.category WHERE idCategory = ?";
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
    public List<Category> selectAllCategory() {
        List<Category> list = new ArrayList<>();
        Connection connection = getConnection();
        try{
            Statement statement = connection.createStatement();

            System.out.println(this.getClass() + " selectAllType : " + statement);
            ResultSet rs = statement.executeQuery(SELECT_ALL_IDTYPE);
            while (rs.next()){
                Long idType = rs.getLong("idcategory");
                String typ = rs.getString("category");


                Category category = new Category(idType, typ);
                list.add(category);
            }
            connection.close();
        }catch (SQLException sqlException){

        }
        return list;
    }

    @Override
    public Category selectIdType(Long idCategory) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(TYPE_BY_ID);
        preparedStatement.setLong(1, idCategory);

        System.out.println(this.getClass() + " selectIdType : " + preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Long idType1 = rs.getLong("idcategory");
            String typ = rs.getString("category");


            Category category = new Category(idType1, typ);
            return category;
        }
        return null;
    }
}
