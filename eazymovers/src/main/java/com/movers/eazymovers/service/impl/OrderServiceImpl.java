package com.movers.eazymovers.service.impl;

import com.movers.eazymovers.common.dto.OrderDTO;
import com.movers.eazymovers.common.enums.OrderStatus;
import com.movers.eazymovers.common.response.Result;
import com.movers.eazymovers.common.response.ResultList;
import com.movers.eazymovers.common.util.EasyMoversBeanUtils;
import com.movers.eazymovers.common.util.ThreadLocalContextHolder;
import com.movers.eazymovers.dal.entity.*;
import com.movers.eazymovers.dal.repository.OrderHistoryRepository;
import com.movers.eazymovers.dal.repository.OrderLocationRepository;
import com.movers.eazymovers.dal.repository.OrderRepository;
import com.movers.eazymovers.service.OrderNotificationService;
import com.movers.eazymovers.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderLocationRepository orderLocationRepository;

    @Autowired
    OrderHistoryRepository orderHistoryRepository;

    @Autowired
    OrderNotificationService notificationService;

    @Override
    @Transactional
    public Result updateOrder(OrderDTO orderDTO) {
        Result result = new Result();
        if(null == orderDTO.getId() || orderDTO.getId().equals(0)){
            result.setSuccess(false);
            result.setMessage("Invalid order update!");
            return result;
        }

        Order order = new Order();
        EasyMoversBeanUtils.copyNonNullProperties(orderDTO,order);
        order.setLastUpdatedTime(new Date());
        User currentUser = ThreadLocalContextHolder.getCurrentUser();
        Order existing = orderRepository.findById(orderDTO.getId()).get();

        // a provider accepted and pending to complete
        if(null != orderDTO.getStatus() &&
                OrderStatus.ACCEPTED.equals(OrderStatus.fromVale(orderDTO.getStatus())) &&
                currentUser.getIsVehicleOwner()){
            //check if the vehicle owner has accepted another order during same time
            if(hasAnotherOrderAccepted(currentUser,existing)){
                result.setSuccess(false);
                result.setMessage("Cant accept multiple orders to fulfill in same time");
                return result;
            }
            order.setProvider(currentUser);

            if(existing.getProvider() != null &&
                    OrderStatus.ACCEPTED.equals(OrderStatus.fromVale(existing.getStatus()))){
                //someone else already accepted the order
                result.setSuccess(false);
                result.setMessage("Already accepted by another driver!");
                return result;
            }else{
                orderRepository.save(order);
                notificationService.notifyToConsumer(order);
                Order updated = orderRepository.findById(orderDTO.getId()).get();
                updateOrderHistory("Order accepted",updated);

                result.setMessage("Order accepted successfully.");
                result.setSuccess(true);
                result.setData(updated);
                return result;
            }
        }else if(OrderStatus.CANCEL.equals(OrderStatus.fromVale(orderDTO.getStatus())) &&
                currentUser.getIsVehicleOwner()){
            //when cancel a order by driver, system has to notify other drivers
            //if reorder count < #xx to avoid deadlock
            order.setProvider(null);
            Integer resubmit = order.getResubmit();
            if(null != resubmit){
                resubmit = resubmit+1;
            }else{
               resubmit=1;
            }
            order.setResubmit(resubmit);
            orderRepository.save(order);
            Order updated = orderRepository.findById(orderDTO.getId()).get();
            if(resubmit < 2) {
                //only one times allow to resubmit by system
                notificationService.notifyToServiceProviders(updated);
            }
            updateOrderHistory("Order Canceled and reorder notified",updated);

            result.setMessage("Order cancelled");
            result.setSuccess(true);
            result.setData(updated);
            return result;
        }else{
            orderRepository.save(order);
            Order updated = orderRepository.findById(orderDTO.getId()).get();
            updateOrderHistory("",updated);

            result.setMessage("Order updated");
            result.setSuccess(true);
            result.setData(updated);
            return result;
        }
    }

    private void updateOrderHistory(String comments, Order order){
        OrderHistory history = new OrderHistory();
        history.setStatus(order.getStatus());
        history.setComments(comments);
        history.setOrder(order);
        history.setUpdatedTime(order.getLastUpdatedTime());
        orderHistoryRepository.save(history);
    }

    private boolean hasAnotherOrderAccepted(User user, Order existing) {
        //TODO
        return false;
    }

    @Override
    @Transactional
    public Result createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        order.setConsumer(ThreadLocalContextHolder.getCurrentUser());

        //persist from and to locations
        OrderLocation from = new OrderLocation();
        BeanUtils.copyProperties(orderDTO.getFromAddress(),from);
        orderLocationRepository.save(from);
        order.setFromAddress(from);

        OrderLocation to = new OrderLocation();
        BeanUtils.copyProperties(orderDTO.getToAddress(),to);
        orderLocationRepository.save(to);
        order.setFromAddress(to);

        order.setStatus(OrderStatus.getValue(OrderStatus.SUBMITED));
        order.setCreatedTime(new Date());
        order.setLastUpdatedTime(new Date());
        orderRepository.save(order);

        OrderHistory history = new OrderHistory();
        history.setStatus(order.getStatus());
        history.setComments("Order Initiated");
        history.setOrder(order);
        history.setUpdatedTime(order.getCreatedTime());
        orderHistoryRepository.save(history);

        Notification notification = notificationService.notifyToServiceProviders(order);

        /*
        //TBD whether need below lists or not
        List<OrderHistory> historyList = new ArrayList<>();
        historyList.add(history);
        order.setHistoryList(historyList);

        List<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        order.setNotificationList(notificationList);
        orderRepository.save(order);

         */
        Result result = new Result();
        result.setData(order);
        result.setMessage("Order placed successfully");
        result.setSuccess(true);
        return result;
    }

    @Override
    public ResultList<Order> listUserOrders(Long userId,Integer pageNo, Integer pageSize) {
        User currentUser = ThreadLocalContextHolder.getCurrentUser();
        ResultList resultList = new ResultList();
        resultList.setCurrentPage(pageNo);
        resultList.setPageSize(pageSize);

        if(!userId.equals(currentUser.getUserId())){
            resultList.setMessage("Please login");
            resultList.setSuccess(false);
        }else{
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<Order> page = orderRepository.findUserOrders(pageable, userId);
            if(null != page && null != page.getContent()){
                resultList.setSuccess(true);
                resultList.setTotalRecords(page.getTotalElements());
                resultList.setData(page.getContent());
            }else{
                resultList.setSuccess(true);
                resultList.setMessage("No matching records");
            }
        }
        return resultList;
    }

    @Override
    public ResultList<Order> listPendingAcceptOrders(Long userId, Integer pageNo, Integer pageSize) {
        User currentUser = ThreadLocalContextHolder.getCurrentUser();
        ResultList resultList = new ResultList();
        resultList.setCurrentPage(pageNo);
        resultList.setPageSize(pageSize);

        if(!userId.equals(currentUser.getUserId())){
            resultList.setMessage("Please login");
            resultList.setSuccess(false);
        }else if(!currentUser.getIsVehicleOwner()){
            resultList.setMessage("Only vehicle owners allowed");
            resultList.setSuccess(false);
        }else{
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<Order> page = orderRepository.findSubmittedRequests(pageable, userId);
            if(null != page && null != page.getContent()){
                resultList.setSuccess(true);
                resultList.setTotalRecords(page.getTotalElements());
                resultList.setData(page.getContent());
            }else{
                resultList.setSuccess(true);
                resultList.setMessage("No matching records");
            }
        }
        return resultList;
    }
}
