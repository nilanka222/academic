package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue
    private Long vehicleId;

    @Column(name="reg_no")
    private String regNo;

    @OneToOne(targetEntity = VehicleType.class,cascade = CascadeType.ALL)
    @JoinColumn(name="vehicle_type_id")
    private VehicleType vehicleType;

    @OneToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User owner;

}
