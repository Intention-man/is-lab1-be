package com.example.prac.DTO.data;

import com.example.prac.model.dataEntity.Coordinates;
import com.example.prac.model.dataEntity.FuelType;
import com.example.prac.model.dataEntity.VehicleType;
import lombok.Data;

@Data
public class VehicleDTO {
    private int id;
    private String name;
    private Coordinates coordinates;
    private VehicleType type;
    private Integer enginePower;
    private long numberOfWheels;
    private long capacity;
    private Long distanceTravelled;
    private Double fuelConsumption;
    private FuelType fuelType;
}