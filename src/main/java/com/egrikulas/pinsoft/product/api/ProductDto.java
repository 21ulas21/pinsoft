package com.egrikulas.pinsoft.product.api;

import com.egrikulas.pinsoft.rating.api.RatingDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ProductDto {

    private final String id;
    private final Date created;
    private final Date modified;
    private final String title;
    private final Double price;
    private final String description;
    private final String image;
    private final String category;
    private final RatingDto rating;
}
