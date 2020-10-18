package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="order")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name="consumer")
    private User consumer;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name="service_provider")
    private User provider;

    @OneToOne(targetEntity = OrderLocation.class, cascade = CascadeType.ALL)
    @JoinColumn(name="location_from")
    private OrderLocation fromAddress;

    @OneToOne(targetEntity = OrderLocation.class, cascade = CascadeType.ALL)
    @JoinColumn(name="location_to")
    private OrderLocation toAddress;

    @Column(name="distance")
    private Double distance;

    @Column(name="vehicle_type_id")
    private Integer vehicleTypeId;

    @Column(name="service_need_date_time")
    private Date serviceNeedDate;

    @Column(name="status")
    private Integer status;

    @Column(name="comments")
    private String comments;

    @OneToMany(targetEntity = Notification.class,cascade = CascadeType.ALL)
    private List<Notification> notificationList;

    @Column(name="last_updated_time")
    private Date lastUpdatedTime;

    @Column(name="created_time")
    private Date createdTime;

    @OneToMany(targetEntity = OrderHistory.class,cascade = CascadeType.ALL)
    private List<OrderHistory> historyList;

}


