package com.movers.eazymovers.service;

import com.movers.eazymovers.dal.entity.Notification;
import com.movers.eazymovers.dal.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderNotificationService {
    Notification notifyToServiceProviders(Order order);

    Notification notifyToConsumer(Order order);
}
