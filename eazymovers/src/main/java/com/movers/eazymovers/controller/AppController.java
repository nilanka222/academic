package com.movers.eazymovers.controller;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.common.dto.UserRoleDTO;
import com.movers.eazymovers.common.dto.VehicleDTO;
import com.movers.eazymovers.common.response.Result;
import com.movers.eazymovers.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/easymovers")
public class AppController {
    @Autowired
    AppService appService;

    @GetMapping(value="/vehicle/by/owner")
    public VehicleDTO findVehicleByOwner(@RequestParam Long userId){
        return appService.findVehicleByOwner(userId);
    }

    @GetMapping(value="/vehicle/by/type")
    public List<VehicleDTO> findVehicleByType(@RequestParam Integer vehicleTypeId){
        return appService.findVehicleByType(vehicleTypeId);
    }

}
