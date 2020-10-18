package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.dal.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle,Long> {
    Vehicle findByOwnerUserId(Long userId);

    List<Vehicle> findByVehicleTypeId(Integer id);

}
