package com.egrikulas.pinsoft.rating.api;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RatingDto {

    private final String id;
    private final Date created;
    private final Date modified;
    private final Double rate;
    private final Integer count;

}
