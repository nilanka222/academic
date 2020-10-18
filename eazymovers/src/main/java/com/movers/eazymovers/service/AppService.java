package com.movers.eazymovers.service;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.common.dto.UserRoleDTO;
import com.movers.eazymovers.common.dto.VehicleDTO;
import com.movers.eazymovers.common.response.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppService {

    VehicleDTO findVehicleByOwner(Long userId);

    List<VehicleDTO> findVehicleByType(Integer vehicleTypeId);

}
