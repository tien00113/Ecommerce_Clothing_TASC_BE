package com.tasc.clothing.request;

import java.math.BigDecimal;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.tasc.clothing.model.Cart;
import com.tasc.clothing.model.Payment;
import com.tasc.clothing.model.User;

import lombok.Data;
import lombok.Getter;

@Data
public class OrderRequest {
    private Cart cart;
    private String note;
    private Payment payment;
    // public BigDecimal getTotalSalePrice() {
    //     return cart.get.stream()
    //                .map(Item::getPrice)
    //                .reduce(BigDecimal.ZERO, BigDecimal::add);
    // }
}
