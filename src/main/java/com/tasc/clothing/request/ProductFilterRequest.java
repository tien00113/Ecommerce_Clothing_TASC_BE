package com.tasc.clothing.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterRequest {
    private int minPrice;
    private int maxPrice;
    private Long categoryId;
    private String color;
    private int page;
}
