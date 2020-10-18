package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name="function_permission")
public class FunctionPermission implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="function_name")
    private String functionName;

    @ManyToMany
    private List<UserRole> userRoleList;


}
