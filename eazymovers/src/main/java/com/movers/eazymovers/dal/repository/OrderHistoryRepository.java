package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.OrderHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends CrudRepository<OrderHistory,Long> {
}
