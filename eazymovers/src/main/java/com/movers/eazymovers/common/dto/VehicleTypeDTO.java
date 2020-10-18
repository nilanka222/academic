package com.movers.eazymovers.common.dto;

import lombok.Data;


@Data
public class VehicleTypeDTO {

    private Integer id;
    private String name;
    private String capacity;
    private Double minFare;
    private Double perKmFare;
}
