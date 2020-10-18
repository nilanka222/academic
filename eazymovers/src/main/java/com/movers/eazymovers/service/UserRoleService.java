package com.movers.eazymovers.service;

import com.movers.eazymovers.common.dto.UserRoleDTO;
import com.movers.eazymovers.common.response.Result;
import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {
    Result<UserRoleDTO> saveUserRole(UserRoleDTO userRoleDTO);

    Result<UserRoleDTO> updateUserRole(UserRoleDTO userRoleDTO);
}
