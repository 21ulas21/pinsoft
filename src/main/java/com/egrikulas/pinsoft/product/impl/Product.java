package com.egrikulas.pinsoft.product.impl;

import com.egrikulas.pinsoft.library.entity.AbstractEntity;
import com.egrikulas.pinsoft.rating.impl.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Product.TABLE)
public class Product extends AbstractEntity {
    public static final String TABLE="product";
    public static final String COL_TITLE="title";
    public static final String COL_PRICE="price";
    public static final String COL_DESCRIPTION="description";
    public static final String COL_CATEGORY="category";
    public static final String COL_IMAGE_URL="image_url";
    public static final String COL_RATING_ID="rating_id";
    @Column(name = COL_TITLE, length = 1000)
    private String title;
    @Column(name = COL_PRICE)
    private Double price;
    @Column(name = COL_DESCRIPTION, length = 1000)
    private String description;
    @Column(name = COL_IMAGE_URL, length = 1000)
    private String image;
    @Column(name = COL_CATEGORY)
    private String category;
    @OneToOne
    @JoinColumn(name = COL_RATING_ID)
    private Rating rating;
}
