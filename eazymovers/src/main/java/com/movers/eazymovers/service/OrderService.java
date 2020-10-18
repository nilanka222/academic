package com.movers.eazymovers.service;

import com.movers.eazymovers.common.dto.OrderDTO;
import com.movers.eazymovers.common.response.Result;
import com.movers.eazymovers.common.response.ResultList;
import com.movers.eazymovers.dal.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface OrderService {

    Result updateOrder(OrderDTO orderDTO);

    Result createOrder(OrderDTO orderDTO);

    ResultList<Order> listUserOrders(Long userId, Integer pageNo, Integer pageSize);

    ResultList<Order> listPendingAcceptOrders(Long userId, Integer pageNo, Integer pageSize);
}
