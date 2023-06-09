package com.egrikulas.pinsoft.rating.impl;

import com.egrikulas.pinsoft.rating.api.RatingDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingRequest {


    private final Double rate;
    private final Integer count;

    public RatingDto toDto(){
        return RatingDto.builder()
                .rate(rate)
                .count(count)
                .build();
    }

}
