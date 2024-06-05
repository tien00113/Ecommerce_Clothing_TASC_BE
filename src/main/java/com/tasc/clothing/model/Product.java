package com.tasc.clothing.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    private String images;
    private String description;

    private Boolean active;

    // private String size;
    // private String color;
    // @ElementCollection
    // @CollectionTable(name = "product_details", joinColumns = @JoinColumn(name =
    // "product_id"))
    // @MapKeyColumn(name = "detail_type")
    // @Column(name = "detail_value")
    // private Map<String, String> details = new HashMap<>();

    private String color;
    private String size;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public void addImage(String imageUrl) {
        if (this.images == null || this.images.isEmpty()) {
            this.images = imageUrl;
        } else {
            this.images += "," + imageUrl;
        }
    }

    public List<String> getImageList() {
        if (this.images == null || this.images.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(this.images.split(","));
    }

    public void addColor(String color){
        if(this.color == null || this.color.isEmpty()) {
            this.color = color;
        } else {
            this.color += "," + size;
        }
    }

    public List<String> getColorList() {
        if (this.color == null || this.color.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(this.color.split(","));
    }

    public void addSize(String size){
        if(this.size == null || this.size.isEmpty()) {
            this.size = size;
        } else {
            this.size += "," + size;
        }
    }

    public List<String> getSizeList() {
        if (this.size == null || this.size.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(this.size.split(","));
    }

}
