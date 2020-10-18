package com.movers.eazymovers.service;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.common.response.Result;

public interface UserService {
    Result registerUser(UserDTO userDTO);

    Result updateUser(UserDTO userDTO);

    Result authenticateUser(UserDTO userDTO);

}
