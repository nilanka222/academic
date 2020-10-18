package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {
    Page<Order> findByConsumerUserIdOrProviderUserId(Long userId, Pageable pageable);
}
