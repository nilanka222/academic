package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="user_address")
@SequenceGenerator(name="ADDR_SEQ", sequenceName="addr_sequence")
public class UserAddress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADDR_SEQ")
    private Long id;

    @Column(name="address_line1")
    private String addressLine1;

    @Column(name="address_line2")
    private String addressLine2;

    @Column(name="city")
    private String city;

    @Column(name="district")
    private String district;



}
