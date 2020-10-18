package com.movers.eazymovers.common.dto;

import com.movers.eazymovers.dal.entity.Order;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class NotificationDTO implements Serializable {

    private Long id;

    private Order order;

    private String message;

    private Date sentDateTime;

}
