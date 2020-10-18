package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Data
@Table(name="vehicle_type")
public class VehicleType {

    @Id
    @GeneratedValue
    @Column(name="vehicle_type_id")
    private Integer id;

    @Column(name="vehicle_type_name")
    private String name;

    @Column(name="capacity")
    private String capacity;

    @Column(name="min_fare")
    private Double minFare;

    @Column(name="per_km_fare")
    private Double perKmFare;
}
