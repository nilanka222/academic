package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.OrderLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLocationRepository extends CrudRepository<OrderLocation,Long> {
}
