package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.UserAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdressRepository extends CrudRepository<UserAddress,Long> {
}
