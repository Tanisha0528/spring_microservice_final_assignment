package com.shop.management.service.shopManagementService.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private int pid;

    private String name;
    private String price;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Product() {
    }

    public Product(int pid, String name, String price, String description, Category category) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product(String name, String price, String description, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = new Category(category.getId(), category.getName(), category.getDescription());
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}