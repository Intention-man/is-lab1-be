package com.example.prac.repository.data;

import com.example.prac.model.dataEntity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}