package com.tasc.clothing.request;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.Product;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemRequest {
    private Product product;
    private Cart cart;
    private String color;
    private String size;
}
