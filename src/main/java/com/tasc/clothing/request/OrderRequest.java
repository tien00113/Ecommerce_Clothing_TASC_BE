package com.tasc.clothing.request;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.Payment;

import lombok.Data;

@Data
public class OrderRequest {
    private Cart cart;
    private String note;
    private String address;
    // private Payment payment;
}
