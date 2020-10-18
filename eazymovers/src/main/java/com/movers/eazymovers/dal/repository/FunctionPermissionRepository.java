package com.movers.eazymovers.dal.repository;

import com.movers.eazymovers.dal.entity.FunctionPermission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionPermissionRepository extends CrudRepository<FunctionPermission,Long> {
}
