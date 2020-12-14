package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="user")
@SequenceGenerator(name="USER_SEQ", sequenceName="user_sequence")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_SEQ")
    private Long userId;

    @Column(name="user_name")
    private String username;

    @Column(name="password")
    private String password;

    @OneToOne(targetEntity = UserRole.class,cascade = CascadeType.ALL)
    @JoinColumn(name="role_id")
    private UserRole userRole;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="mobile_no")
    private String mobileNumber;

    @OneToOne(targetEntity = UserAddress.class,cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private UserAddress address;

    @Column(name="status")
    private Integer status;

    @Column(name="rating")
    private Double ratingScore;

    @Column(name="last_ligin")
    private Date lastLoginTime;

    @Column(name="is_vehicle_owner")
    private Boolean isVehicleOwner;

}
