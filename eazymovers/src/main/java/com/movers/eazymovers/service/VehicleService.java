package com.movers.eazymovers.service;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.common.dto.VehicleDTO;
import com.movers.eazymovers.common.dto.VehicleTypeDTO;
import com.movers.eazymovers.common.response.ResultList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
    VehicleDTO addVehicle(VehicleDTO vehicleDTO);

    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);

    void deleteVehicle(Long id);

    VehicleDTO findVehicleByOwner(Long userId);

    List<VehicleDTO> findVehicleByVehicleType(Integer typeId);

    VehicleTypeDTO addVehicleType(VehicleTypeDTO vehicleTypeDTO);

    VehicleTypeDTO updateVehicleType(VehicleTypeDTO vehicleTypeDTO);

    VehicleTypeDTO findVehicleTypeById(Integer id);

    void deleteVehicleType(Integer id);

    List<UserDTO> findOwnersByVehicleType(Integer typeId);

    ResultList findVehicle(Integer pageNo, Integer pageSize, Long userId, Integer vehicleTypeId);
}
