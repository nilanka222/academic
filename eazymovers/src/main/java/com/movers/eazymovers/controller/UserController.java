package com.movers.eazymovers.controller;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.common.dto.UserRoleDTO;
import com.movers.eazymovers.common.response.Result;
import com.movers.eazymovers.service.UserRoleService;
import com.movers.eazymovers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/easymovers/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @PostMapping(value="/register")
    public Result<UserDTO> registerUser(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }

    @PostMapping(value="/update")
    public Result<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @PostMapping(value = "/authenticate")
    public Result authenticateUser(@RequestBody UserDTO userDTO){
        return userService.authenticateUser(userDTO);
    }

    @PostMapping(value="/role/save")
    public Result<UserRoleDTO> saveUserRole(@RequestBody UserRoleDTO userRoleDTO){
        return userRoleService.saveUserRole(userRoleDTO);
    }

    @PostMapping(value="/role/update")
    public Result<UserRoleDTO> updateUserRole(@RequestBody UserRoleDTO userRoleDTO){
        return userRoleService.updateUserRole(userRoleDTO);
    }
}
