package com.movers.eazymovers.common.dto;

import com.movers.eazymovers.dal.entity.User;
import com.movers.eazymovers.dal.entity.VehicleType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class VehicleDTO implements Serializable {

    private Long vehicleId;

    private String regNo;

    private VehicleType vehicleType;

    private UserDTO owner;

}
