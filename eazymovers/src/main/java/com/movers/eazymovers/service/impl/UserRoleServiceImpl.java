package com.movers.eazymovers.service.impl;

import com.movers.eazymovers.common.dto.UserRoleDTO;
import com.movers.eazymovers.common.response.Result;
import com.movers.eazymovers.common.util.EasyMoversBeanUtils;
import com.movers.eazymovers.dal.entity.UserRole;
import com.movers.eazymovers.dal.repository.UserRoleRepository;
import com.movers.eazymovers.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public Result<UserRoleDTO> saveUserRole(UserRoleDTO userRoleDTO) {
        UserRole role = new UserRole();
        BeanUtils.copyProperties(userRoleDTO,role);
        userRoleRepository.save(role);
        userRoleDTO.setId(role.getId());

        Result<UserRoleDTO> result = new Result();
        result.setData(userRoleDTO);
        result.setMessage("Saved successfully");
        result.setSuccess(true);
        return result;
    }

    @Override
    public Result<UserRoleDTO> updateUserRole (UserRoleDTO userRoleDTO) {
        Result result = new Result();

        if(null == userRoleDTO.getId() || userRoleDTO.getId().equals(0)){
            result.setSuccess(false);
            result.setMessage("Non existing user!");
            return result;
        }

        Optional<UserRole> exstingRole = userRoleRepository.findById(userRoleDTO.getId());
        UserRole role = exstingRole.get();
        EasyMoversBeanUtils.copyNonNullProperties(userRoleDTO,role);
        userRoleRepository.save(role);

        result.setSuccess(true);
        result.setMessage("UserRole updated!");
        BeanUtils.copyProperties(role,userRoleDTO);
        result.setData(userRoleDTO);
        return result;
    }
}
