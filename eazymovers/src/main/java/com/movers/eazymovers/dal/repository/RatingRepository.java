package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends PagingAndSortingRepository<Rating,Long> {
}
