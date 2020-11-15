package com.movers.eazymovers.service.impl;

import com.movers.eazymovers.common.dto.UserDTO;
import com.movers.eazymovers.common.dto.VehicleDTO;
import com.movers.eazymovers.common.dto.VehicleTypeDTO;
import com.movers.eazymovers.common.enums.UserStatus;
import com.movers.eazymovers.common.response.ResultList;
import com.movers.eazymovers.common.util.EasyMoversBeanUtils;
import com.movers.eazymovers.dal.entity.Vehicle;
import com.movers.eazymovers.dal.entity.VehicleType;
import com.movers.eazymovers.dal.repository.VehicleRepository;
import com.movers.eazymovers.dal.repository.VehicleTypeRepository;
import com.movers.eazymovers.service.VehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    private static final Logger LOGGER = LogManager.getLogger(VehicleServiceImpl.class);

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Override
    public VehicleDTO addVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO,vehicle);
        vehicleRepository.save(vehicle);
        vehicleDTO.setVehicleId(vehicle.getVehicleId());
        return vehicleDTO;
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        if(null == vehicleDTO.getVehicleId() || vehicleDTO.getVehicleId().equals(0)){
            throw new IllegalArgumentException("Invalid vehicle id");
        }

        Vehicle vehicle = new Vehicle();
        EasyMoversBeanUtils.copyNonNullProperties(vehicleDTO,vehicle);
        vehicleRepository.save(vehicle);

        Optional<Vehicle> updated = vehicleRepository.findById(vehicleDTO.getVehicleId());
        BeanUtils.copyProperties(updated.get(),vehicleDTO);
        return vehicleDTO;
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
        LOGGER.info("vehicle removed id:"+id);
    }

    @Override
    public VehicleDTO findVehicleByOwner(Long userId) {
        Vehicle vehicle = vehicleRepository.findByOwnerUserId(userId);
        VehicleDTO vehicleDTO = new VehicleDTO();
        BeanUtils.copyProperties(vehicle,vehicleDTO);
        return vehicleDTO;
    }

    @Override
    public List<VehicleDTO> findVehicleByVehicleType(Integer id) {
        List<Vehicle> list = vehicleRepository.findByVehicleTypeId(id);
        List<VehicleDTO> dtoList = new ArrayList<>();
        list.forEach(vehicle -> {
            VehicleDTO dto = new VehicleDTO();
            BeanUtils.copyProperties(vehicle,dto);
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override
    public VehicleTypeDTO addVehicleType(VehicleTypeDTO vehicleTypeDTO) {
        VehicleType vehicleType = new VehicleType();
        BeanUtils.copyProperties(vehicleTypeDTO,vehicleType);
        vehicleTypeRepository.save(vehicleType);
        vehicleTypeDTO.setId(vehicleType.getId());
        return vehicleTypeDTO;
    }

    @Override
    public VehicleTypeDTO updateVehicleType(VehicleTypeDTO vehicleTypeDTO) {
        if(null == vehicleTypeDTO.getId() || vehicleTypeDTO.getId().equals(0)){
            throw new IllegalArgumentException("Invalid vehicle type id");
        }
        VehicleType vehicleType = new VehicleType();
        EasyMoversBeanUtils.copyNonNullProperties(vehicleTypeDTO,vehicleType);
        vehicleTypeRepository.save(vehicleType);
        Optional<VehicleType> updated = vehicleTypeRepository.findById(vehicleTypeDTO.getId());
        BeanUtils.copyProperties(updated.get(),vehicleTypeDTO);
        return vehicleTypeDTO;
    }

    @Override
    public VehicleTypeDTO findVehicleTypeById(Integer id){
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
        VehicleTypeDTO dto = new VehicleTypeDTO();
        BeanUtils.copyProperties(vehicleType.get(),dto);
        return dto;
    }

    @Override
    public void deleteVehicleType(Integer id) {
        vehicleTypeRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> findOwnersByVehicleType(Integer typeId) {
        List<VehicleDTO> list = this.findVehicleByVehicleType(typeId);
        List<UserDTO> ownerList = new ArrayList<>();
        for(VehicleDTO dto: list){
            UserDTO user = dto.getOwner();
            if(user.getStatus().equals(UserStatus.getValue(UserStatus.ACTIVE))){
                ownerList.add(user);
            }
        }

        return ownerList;
    }

    @Override
    public ResultList findVehicle(Integer pageNo, Integer pageSize, Long userId, Integer vehicleTypeId) {
        ResultList resultList = new ResultList();
        resultList.setCurrentPage(pageNo);
        resultList.setPageSize(pageSize);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        //we can use criteria query builder if there more dynamic params
        if(null != userId){
            VehicleDTO dto = findVehicleByOwner(userId);
            List list = new ArrayList();
            list.add(dto);
            resultList.setData(list);
            resultList.setSuccess(true);
            resultList.setTotalRecords(1);
        }else if(null != vehicleTypeId){
            Page<Vehicle> page = vehicleRepository.findByVehicleTypeId(pageable,vehicleTypeId);
            if(null != page && null != page.getContent()){
                resultList.setSuccess(true);
                resultList.setTotalRecords(page.getTotalElements());
                resultList.setData(page.getContent());
            }else{
                resultList.setSuccess(true);
                resultList.setMessage("No matching records");
            }
        }else{
            Page<Vehicle> page = vehicleRepository.findAll(pageable);
            if(null != page && null != page.getContent()){
                resultList.setSuccess(true);
                resultList.setTotalRecords(page.getTotalElements());
                resultList.setData(page.getContent());
            }else{
                resultList.setSuccess(true);
                resultList.setMessage("No matching records");
            }
        }
        return resultList;
    }
}
