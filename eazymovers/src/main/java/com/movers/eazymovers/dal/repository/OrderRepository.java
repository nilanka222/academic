package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {
    @Query("Select o from Order o where o.consumer.userId=:userId or o.provider.userId=userId")
    Page<Order> findUserOrders(Pageable pageable, @Param(value="userId")Long userId);

    @Query("Select o from Order o where (o.consumer.userId=:userId or o.provider.userId=userId) and " +
            "(o.status=1 or o.status=3) and o.resubmit <2")
    Page<Order> findSubmittedRequests(Pageable pageable, Long userId);
}
