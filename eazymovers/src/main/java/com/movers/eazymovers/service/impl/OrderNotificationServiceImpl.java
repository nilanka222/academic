package com.movers.eazymovers.service.impl;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.dal.entity.Notification;
import com.movers.eazymovers.dal.entity.Order;
import com.movers.eazymovers.dal.entity.OrderLocation;
import com.movers.eazymovers.dal.entity.User;
import com.movers.eazymovers.dal.repository.NotificationRepository;
import com.movers.eazymovers.service.OrderNotificationService;
import com.movers.eazymovers.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderNotificationServiceImpl implements OrderNotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    VehicleService vehicleService;

    @Override
    public Notification notifyToServiceProviders(Order order) {
        Notification notification = new Notification();
        List<UserDTO> vehicleOwners = vehicleService.findOwnersByVehicleType(order.getVehicleTypeId());
        //VehicleTypeDTO vehicleTypeDTO = vehicleService.findVehicleTypeById(order.getVehicleTypeId());

        OrderLocation from = order.getFromAddress();
        OrderLocation to = order.getToAddress();
        String fromAddress = from.getAddressLine1()+", "+from.getAddressLine2()+", "+from.getCity();
        String toAddress = to.getAddressLine1()+", "+to.getAddressLine2()+", "+to.getCity();
        String message = "Delivery order has been placed from:"+fromAddress+" to:"+toAddress+
                "Please login to Easy Movers and accept the order";

        //notify to only relevant vehicle type owners
        broadcastToVehicleTypeOwners(vehicleOwners,message);

        notification.setMessage(message);
        notification.setOrder(order);
        notification.setSentDateTime(new Date());
        notificationRepository.save(notification);
        return notification;
    }

    private void broadcastToVehicleTypeOwners(List<UserDTO> vehicleOwners, String message) {
        //TODO
    }

    @Override
    public Notification notifyToConsumer(Order order) {
        Notification notification = new Notification();
        User serviceProvder = order.getProvider();
        String message = "Your order has been accepted by:"+serviceProvder.getFirstName()+
                " Please contact your driver on:"+serviceProvder.getMobileNumber();
        sendSMSToServiceConsumer(order.getConsumer(),message);
        notification.setMessage(message);
        notification.setOrder(order);
        notification.setSentDateTime(new Date());
        notificationRepository.save(notification);
        return notification;
    }

    private void sendSMSToServiceConsumer(User consumer, String message) {
        //TODO
    }
}
