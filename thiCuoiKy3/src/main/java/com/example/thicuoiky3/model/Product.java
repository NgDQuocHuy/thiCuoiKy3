package com.example.thicuoiky3.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String color;
    private String info;
    private Long idCategory;

    public Product() {}

    public Product(String name, BigDecimal price, int quantity, String color, String info, Long idCategory) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.info = info;
        this.idCategory = idCategory;
    }

    public Product(Long id, String name, BigDecimal price, int quantity, String color, String info, Long idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.info = info;
        this.idCategory = idCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = "Name cannot be blank")
    @Length(min = 1, max = 30, message = "Name must be more than 1 and less than 45 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Price cannot be Null")
    @DecimalMin(value = "1000",message = "Money must be more than 1000")
    @DecimalMax(value = "100000000", message = "Money must be less than 100000000")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = "Quantity cannot be blank")
    @Min(value = 1, message = "Quantity must be more than 1")
    @Max(value = 100, message = "Quantity must be less than 100")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Length (max = 200, message = "Infomation must be less than 200 characters")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @NotNull(message = "Type cannot be Null")
    @Min(value = 1, message = "Id of type not found")
    @Max(value = 3, message = "Id of type not found")
    public Long getIdCategory() {
        return idCategory;
    }

    public void setidCategory(Long idCategory) {
        this.idCategory = idCategory;
    }
}
