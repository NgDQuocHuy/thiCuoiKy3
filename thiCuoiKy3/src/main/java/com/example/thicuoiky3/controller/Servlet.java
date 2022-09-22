package com.example.thicuoiky3.controller;

import com.example.thicuoiky3.model.Category;
import com.example.thicuoiky3.model.Product;
import com.example.thicuoiky3.service.CartegoryService;
import com.example.thicuoiky3.service.ICategory;
import com.example.thicuoiky3.service.IProduct;
import com.example.thicuoiky3.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@WebServlet(name = "Servlet", urlPatterns = {"/products", ""} )
public class Servlet extends HttpServlet {
    IProduct iProduct;
    List<Product> products;
    ICategory iCategory;
    List<Category> categories;


    @Override
    public void init() throws ServletException {
        iProduct = new ProductService();
        iCategory = new CartegoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    showCreateProductForm(req, resp);
                    break;
                case "edit":
                    showEditProductForm(req, resp);
                case "delete":
                    showDeleteForm(req, resp);
                    break;
                case "view":
//                    viewCustomer(req, resp);
                    break;
                default:
                    listProduct(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        categories = this.iCategory.selectAllCategory();
        Long id = Long.parseLong(req.getParameter("id"));
        Product product = this.iProduct.selectProduct(id);

        RequestDispatcher requestDispatcher;

        if (product == null) {
            requestDispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("product", product);
            req.setAttribute("categories", categories);
            requestDispatcher = req.getRequestDispatcher("views/delete.jsp");
        }

        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditProductForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher;
        categories = this.iCategory.selectAllCategory();
        Long id = Long.parseLong(req.getParameter("id"));
        Product product = this.iProduct.selectProduct(id);
        if (product == null) {
            requestDispatcher = req.getRequestDispatcher("/views/error-404.jsp");
        } else if (product.getId() != id) {
            req.setAttribute("categories", categories);
            requestDispatcher = req.getRequestDispatcher("index.jsp");
        } else {
            req.setAttribute("product", product);
            req.setAttribute("categories", categories);
            requestDispatcher = req.getRequestDispatcher("/views/edit.jsp");
        }
        requestDispatcher.forward(req, resp);
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String search = "";
        categories = this.iCategory.selectAllCategory();

        if (req.getParameter("search") != null) {
            search = req.getParameter("search");
        }
        products = this.iProduct.selectAllProduct(search);

        req.setAttribute("search", search);
        req.setAttribute("products", products);
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showCreateProductForm(HttpServletRequest req, HttpServletResponse resp) {
        categories = this.iCategory.selectAllCategory();
        req.setAttribute("categories", categories);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/create.jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    createProduct(req, resp);
                    break;
                case "edit":
                    updateProduct(req, resp);
                    break;
                case "delete":
                    deleteProduct(req, resp);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Product product = this.iProduct.selectProduct(id);
        categories = this.iCategory.selectAllCategory();
        RequestDispatcher requestDispatcher;

        if (product == null) {
            requestDispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            this.iProduct.deleteProduct(id);
            req.setAttribute("message", "Product was deleted");
            req.setAttribute("categories", categories);
            requestDispatcher = req.getRequestDispatcher("/views/view-deleted.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();
        categories = this.iCategory.selectAllCategory();
        RequestDispatcher requestDispatcher;
        Long id = Long.parseLong(req.getParameter("id"));

        Product product = this.iProduct.selectProduct(id);
        if (product == null) {
            requestDispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            try {
                String name = req.getParameter("name");
                product.setName(name);
                Long category = Long.parseLong(req.getParameter("category"));
                product.setidCategory(category);
                BigDecimal price = new BigDecimal(req.getParameter("price"));
                product.setPrice(price);
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                product.setQuantity(quantity);
                String info = req.getParameter("info");
                product.setInfo(info);
                String color = req.getParameter("color");
                product.setColor(color);

                ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                Validator validator = validatorFactory.getValidator();
                Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

                if (!constraintViolations.isEmpty()) {
                    for (ConstraintViolation<Product> item : constraintViolations) {
                        errors.add(item.getMessage());
                    }
                    req.setAttribute("errors", errors);
                    req.setAttribute("product", product);
                    req.setAttribute("categories", categories);
                } else {
                    if (iCategory.selectIdType(category) != null) {
                        if (iProduct.selectProduct(id) != null) {
                            this.iProduct.updateProduct(id, product);
                            req.setAttribute("categories", categories);
                            req.setAttribute("product", product);
                            req.setAttribute("message", "Product information was updated");
                        } else {
                            requestDispatcher = req.getRequestDispatcher("error-404.jsp");
                        }
                    } else {
                        errors.add("Product type is not available");
                        req.setAttribute("errors", errors);
                        req.setAttribute("product", product);
                        req.setAttribute("categories", categories);
                    }
                }
                requestDispatcher = req.getRequestDispatcher("/views/edit.jsp");
                requestDispatcher.forward(req, resp);

            } catch (NumberFormatException numberFormatException) {
                errors.add("Input format");
                req.setAttribute("errors", errors);
                req.setAttribute("product", product);
                req.setAttribute("categories", categories);
                requestDispatcher = req.getRequestDispatcher("/views/edit.jsp");
                requestDispatcher.forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        categories = this.iCategory.selectAllCategory();
        Product product = new Product();
        RequestDispatcher requestDispatcher;
        try {
            String name = req.getParameter("name");
            product.setName(name);
            BigDecimal price = new BigDecimal(req.getParameter("price"));
            product.setPrice(price);
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            product.setQuantity(quantity);
            String color = req.getParameter("color");
            product.setColor(color);
            String info = req.getParameter("info");
            product.setInfo(info);
            Long category = Long.parseLong(req.getParameter("category"));
            product.setidCategory(category);


            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);

            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<Product> item : constraintViolations) {
                    errors.add(item.getMessage());
                }
                req.setAttribute("errors", errors);
                req.setAttribute("product", product);
                req.setAttribute("categories", categories);
            } else {
                if (iCategory.selectIdType(category) != null) {
                    this.iProduct.insertProduct(product);
                    req.setAttribute("categories", categories);
                    req.setAttribute("message", "New product was created");
                } else {
                    errors.add("Product type is not available");
                    req.setAttribute("errors", errors);
                    req.setAttribute("product", product);
                    req.setAttribute("categories", categories);
                }
            }
            requestDispatcher = req.getRequestDispatcher("/views/create.jsp");
            requestDispatcher.forward(req, resp);
        } catch (NumberFormatException numberFormatException) {
            errors.add("Input format");
            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
            req.setAttribute("categories", categories);
            requestDispatcher = req.getRequestDispatcher("/views/create.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
