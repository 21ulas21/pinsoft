package com.egrikulas.pinsoft.rating.impl;

import com.egrikulas.pinsoft.library.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Rating.TABLE)
public class Rating extends AbstractEntity {

    public static final String TABLE="rating";
    public static final String COL_RATE="rate";
    public static final String COL_COUNT="count";
    @Column(name = COL_RATE)
    private Double rate;
    @Column(name = COL_COUNT)
    private Integer count;
}
