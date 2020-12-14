package com.movers.eazymovers.service;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.common.response.JwtResponse;
import com.movers.eazymovers.common.response.Result;
import com.movers.eazymovers.dal.entity.User;

public interface UserService {
    Result registerUser(UserDTO userDTO);

    Result updateUser(UserDTO userDTO);

    Result<JwtResponse> authenticateUser(UserDTO userDTO) throws Exception;

    User findByUsername(String username);
}
