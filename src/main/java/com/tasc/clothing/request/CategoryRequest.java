package com.tasc.clothing.request;

import lombok.Getter;

@Getter
public class CategoryRequest {
    private Long parentCategoryId;
    private String name;
}
