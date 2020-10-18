package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Long> {
}
