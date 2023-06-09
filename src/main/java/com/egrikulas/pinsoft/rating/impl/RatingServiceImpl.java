package com.egrikulas.pinsoft.rating.impl;

import com.egrikulas.pinsoft.rating.api.RatingDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl {

    private final RatingRepository repository;

    public Rating saveRating(RatingDto dto){

       return repository.save(toEntity(new Rating(),dto ));

    }

    public Rating getRatingById(String id){
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Kayıt Bulunamadı"));
    }
    public RatingDto toDto(Rating rating){
        return RatingDto.builder()
                .id(rating.getId())
                .count(rating.getCount())
                .created(rating.getCreated())
                .rate(rating.getRate())
                .modified(rating.getModified())
                .build();
    }

    public Rating toEntity(Rating rating, RatingDto dto){
        rating.setRate(dto.getRate());
        rating.setCount(dto.getCount());
        return rating;
    }


}
