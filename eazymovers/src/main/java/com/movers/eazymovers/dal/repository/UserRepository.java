package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserName(String userName);
}
