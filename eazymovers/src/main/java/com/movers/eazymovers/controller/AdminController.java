package com.movers.eazymovers.controller;

import com.movers.eazymovers.common.dto.VehicleDTO;
import com.movers.eazymovers.common.response.ResultList;
import com.movers.eazymovers.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/easymovers")
public class AdminController {
    @Autowired
    VehicleService vehicleService;

    @GetMapping(value="/find/vehicle")
    public ResultList findVehicle(
            @RequestParam Integer pageNo,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Integer vehicleTypeId ){
        return vehicleService.findVehicle(pageNo,pageSize,userId,vehicleTypeId);
    }


}
