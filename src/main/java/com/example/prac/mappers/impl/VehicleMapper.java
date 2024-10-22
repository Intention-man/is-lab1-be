package com.example.prac.mappers.impl;

import com.example.prac.DTO.data.VehicleDTO;
import com.example.prac.mappers.Mapper;
import com.example.prac.model.dataEntity.Vehicle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper implements Mapper<Vehicle, VehicleDTO> {
    private final ModelMapper modelMapper;

    public VehicleMapper() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public VehicleDTO mapTo(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public Vehicle mapFrom(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }
}
