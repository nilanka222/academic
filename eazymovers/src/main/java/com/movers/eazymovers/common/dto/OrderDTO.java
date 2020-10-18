package com.movers.eazymovers.common.dto;

import com.movers.eazymovers.dal.entity.Notification;
import com.movers.eazymovers.dal.entity.OrderHistory;
import com.movers.eazymovers.dal.entity.OrderLocation;
import com.movers.eazymovers.dal.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class OrderDTO implements Serializable {

    private Long id;

    private User consumer;

    private User provider;

    private OrderLocation fromAddress;

    private OrderLocation toAddress;

    private Double distance;

    private Integer vehicleTypeId;

    private Date serviceNeedDate;

    private Integer status;

    private String comments;

    private List<Notification> notificationList;

    private Date lastUpdatedTime;

    private Date createdTime;

    private List<OrderHistory> historyList;

}


