package com.movers.eazymovers.common.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserAddressDTO implements Serializable {

    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String district;


}
