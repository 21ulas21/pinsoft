package com.egrikulas.pinsoft.product.impl;

import com.egrikulas.pinsoft.product.api.ProductDto;
import com.egrikulas.pinsoft.rating.api.RatingDto;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {


    private final String title;
    private final Double price;
    private final String description;
    private final String image;
    private final String category;
    private final String ratingId;

    public ProductDto toDto(){
        return ProductDto.builder()
                .title(title)
                .price(price)
                .description(description)
                .image(image)
                .category(category)
                .rating(RatingDto.builder().id(ratingId).build())
                .build();
    }




}
