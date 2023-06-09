package com.egrikulas.pinsoft.product.impl;

import com.egrikulas.pinsoft.product.api.ProductDto;
import com.egrikulas.pinsoft.rating.api.RatingDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ProductResponse {

    private final String id;
    private final Date created;
    private final Date modified;
    private final String title;
    private final Double price;
    private final String description;
    private final String image;
    private final String category;
    private final RatingDto rating;


    public static ProductResponse fromDto(ProductDto product){
        return ProductResponse.builder()
                .id(product.getId())
                .created(product.getCreated())
                .modified(product.getModified())
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .image(product.getImage())
                .category(product.getCategory())
                .rating(product.getRating())
                .build();

    }
}
