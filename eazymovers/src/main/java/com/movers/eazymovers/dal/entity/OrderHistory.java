package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name="order_history")
public class OrderHistory implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name="status")
    private Integer status;

    @Column(name="comments")
    private String comments;

    @Column(name="updated_time")
    private Date updatedTime;
}
