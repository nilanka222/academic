package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating,Long> {
}
