package com.egrikulas.pinsoft.rating.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, String> {
}
