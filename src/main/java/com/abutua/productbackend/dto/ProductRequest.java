package com.abutua.productbackend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;

public class ProductRequest {

    @Column(nullable = false, length = 255)
    @NotBlank(message = "Name can not be blank")
    @Size(min=3, max = 255, message = "Name length min=3 and max=255")
    private String name;

    @Column(nullable = false, length = 1024)
    @NotBlank(message = "Description can not be blank")
    @Size(min=3, max = 1024, message = "Description length min=3 and max=1024")
    private String description; 

    private boolean promotion;

    private boolean newProduct;

    @Min(value=0, message = "Price min value = 0")
    private Double price;

    @ManyToOne
    @Valid
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product toEntity() {
        return new Product(name);
    }
}
