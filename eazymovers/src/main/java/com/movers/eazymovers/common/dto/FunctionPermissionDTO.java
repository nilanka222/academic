package com.movers.eazymovers.common.dto;

import com.movers.eazymovers.dal.entity.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
public class FunctionPermissionDTO implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "function_name")
    private String functionName;

    @ManyToMany(targetEntity = UserRole.class, cascade = CascadeType.ALL)
    private List<UserRole> userRoleList;
}