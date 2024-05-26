package com.tasc.clothing.request;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.Product;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItemRequest {
    private Long productId;
    private Cart cart;
    private String color;
    private String size;
}
