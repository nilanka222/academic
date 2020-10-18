package com.movers.eazymovers.controller;

import com.movers.eazymovers.common.dto.OrderDTO;
import com.movers.eazymovers.common.response.Result;
import com.movers.eazymovers.common.response.ResultList;
import com.movers.eazymovers.dal.entity.Order;
import com.movers.eazymovers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/easymovers/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping(value = "/create")
    Result createOrder(@RequestBody OrderDTO orderDTO){
        return orderService.createOrder(orderDTO);
    }

    @PostMapping(value = "/update")
    Result updateOrder(@RequestBody OrderDTO orderDTO){
        return orderService.updateOrder(orderDTO);
    }

    @GetMapping(value = "/list/history")
    ResultList<Order> listUserOrders(@RequestParam Long userId, @RequestParam Integer pageNo, @RequestParam Integer pageSize){
        return orderService.listUserOrders(userId,pageNo,pageSize);
    }

    @GetMapping(value = "/list/pending-accept")
    ResultList<Order> listPendingAccept(@RequestParam Long userId, @RequestParam Integer pageNo, @RequestParam Integer pageSize){
        return orderService.listPendingAcceptOrders(userId,pageNo,pageSize);
    }
}
