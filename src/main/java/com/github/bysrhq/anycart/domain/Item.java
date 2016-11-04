/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author bysrhq
 */
@Entity
@Table
public class Item implements Serializable {
    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    private String id;
    @Length(min = 1, max = 100)
    private String name;
    @Range(min = 1, max = 1000000)
    private int price;
    @Range(min = 1, max = 200)
    private int quantity;
    private String image;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "size_id")
    private Size size;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "color_id")
    private Color color;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "category_id")
    private Category category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    
}
