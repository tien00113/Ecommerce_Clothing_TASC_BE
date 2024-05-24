package com.tasc.clothing.model;

import java.util.HashMap;
import java.util.Map;

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
    private String image;
    private String description;
    // private String size;
    // private String color;
    @ElementCollection
    @CollectionTable(name = "product_details", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "detail_type")
    @Column(name = "detail_value")
    private Map<String, Integer> details = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
