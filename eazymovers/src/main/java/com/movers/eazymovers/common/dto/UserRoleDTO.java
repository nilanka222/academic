package com.movers.eazymovers.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class UserRoleDTO implements Serializable {

    private Integer id;

    private String roleName;

    private Integer status;

    private List functionList;

}
