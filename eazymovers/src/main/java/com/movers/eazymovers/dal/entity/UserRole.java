package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="user_role")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="status")
    private Integer status;

   @ManyToMany(targetEntity = FunctionPermission.class,mappedBy = "userRoleList",cascade = CascadeType.ALL)
    private List functionList;

}
