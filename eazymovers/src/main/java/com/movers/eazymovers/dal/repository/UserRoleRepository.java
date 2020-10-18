package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole,Integer> {
}
