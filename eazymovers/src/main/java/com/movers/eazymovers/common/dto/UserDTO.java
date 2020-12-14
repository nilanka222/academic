package com.movers.eazymovers.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movers.eazymovers.dal.entity.UserAddress;
import com.movers.eazymovers.dal.entity.UserRole;
import com.movers.eazymovers.dal.entity.Vehicle;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserDTO implements Serializable {

    private Long userId;

    private String username;

    @JsonIgnore
    private String password;

    private UserRoleDTO userRole;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private UserAddressDTO address;

    private Integer status;

    private Double ratingScore;

    private Date lastLoginTime;

    private Boolean isVehicleOwner;

    @JsonIgnore
    private VehicleDTO vehicle;

}
