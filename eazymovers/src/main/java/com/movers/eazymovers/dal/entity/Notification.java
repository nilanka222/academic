package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="notification")
public class Notification implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name="message")
    private String message;

    @Column(name="sent_time")
    private Date sentDateTime;

}
