package com.movers.eazymovers.service.impl;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.common.dto.UserRoleDTO;
import com.movers.eazymovers.common.dto.VehicleDTO;
import com.movers.eazymovers.common.response.Result;
import com.movers.eazymovers.service.AppService;
import com.movers.eazymovers.service.UserRoleService;
import com.movers.eazymovers.service.UserService;
import com.movers.eazymovers.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    VehicleService vehicleService;

    @Override
    public VehicleDTO findVehicleByOwner(Long userId) {
        return vehicleService.findVehicleByOwner(userId);
    }

    @Override
    public List<VehicleDTO> findVehicleByType(Integer vehicleTypeId) {
        return vehicleService.findVehicleByVehicleType(vehicleTypeId);
    }
}
