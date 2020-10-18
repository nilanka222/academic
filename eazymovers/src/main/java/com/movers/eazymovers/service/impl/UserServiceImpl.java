package com.movers.eazymovers.service.impl;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.common.enums.UserStatus;
import com.movers.eazymovers.common.response.Result;
import com.movers.eazymovers.common.util.EasyMoversBeanUtils;
import com.movers.eazymovers.common.util.ThreadLocalContextHolder;
import com.movers.eazymovers.dal.entity.User;
import com.movers.eazymovers.dal.entity.UserAddress;
import com.movers.eazymovers.dal.entity.Vehicle;
import com.movers.eazymovers.dal.repository.UserAdressRepository;
import com.movers.eazymovers.dal.repository.UserRepository;
import com.movers.eazymovers.dal.repository.VehicleRepository;
import com.movers.eazymovers.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    UserAdressRepository userAdressRepository;

    @Override
    public Result registerUser(UserDTO userDTO) {
        //TODO validate user properties

        //TODO assign a user role, get from Enum, ...
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setStatus(UserStatus.getValue(UserStatus.PENDING));
        user.setIsVehicleOwner(userDTO.getIsVehicleOwner());

        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(userDTO.getAddress(),address);
        //userAdressRepository.save(address);
        user.setAddress(address);
        userRepository.save(user);

        if(user.getIsVehicleOwner()){
            Vehicle vehicle = new Vehicle();
            BeanUtils.copyProperties(userDTO.getVehicle(),vehicle);
            vehicle.setOwner(user);
            vehicleRepository.save(vehicle);

        }

        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("Registered successfully");
        userDTO.setUserId(user.getUserId());
        userDTO.getAddress().setId(address.getId());
        result.setData(userDTO);

        return result;
    }

    @Override
    public Result updateUser(UserDTO userDTO) {
        Result result = new Result();

        if(null == userDTO.getUserId() || userDTO.getUserId().equals(0)){
            result.setSuccess(false);
            result.setMessage("Non existing user!");
            return result;
        }

        Optional<User> exstingUser = userRepository.findById(userDTO.getUserId());
        User user = exstingUser.get();
        EasyMoversBeanUtils.copyNonNullProperties(userDTO,user);
        userRepository.save(user);

        result.setSuccess(true);
        result.setMessage("User updated!");
        BeanUtils.copyProperties(user,userDTO);
        result.setData(userDTO);
        return result;
    }

    @Override
    public Result authenticateUser(UserDTO userDTO) {
        Result result = new Result();
        if(null == userDTO || StringUtils.isEmpty(userDTO.getUserName()) || StringUtils.isEmpty(userDTO.getPassword())){
            //empty login request
            result.setSuccess(false);
            result.setData(null);
            result.setMessage("Wrong username or password!");
            return result;
        }

        User user = userRepository.findByUserName(userDTO.getUserName());
         if(null == user){
             //username does not match with existing users
             result.setSuccess(false);
             result.setData(null);
             result.setMessage("Wrong username or password!");
             return result;
         }
         String md5Password = getMD5Hash(userDTO.getPassword());
         if(user.getPassword().equals(md5Password)){
             //password matched and authentication success
             result.setSuccess(true);
             result.setData(user);
             result.setMessage("Login Success!");

             //add current user to local thread
             ThreadLocalContextHolder.setCurrentUser(user);
         }else{
             //wrong password
             result.setSuccess(false);
             result.setData(null);
             result.setMessage("Wrong username or password!");
         }

         return result;
    }

    private String getMD5Hash(String passwordString){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordString.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
