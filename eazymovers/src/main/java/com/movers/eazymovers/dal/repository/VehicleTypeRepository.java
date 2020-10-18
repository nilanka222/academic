package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.VehicleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends CrudRepository<VehicleType,Integer> {
}
