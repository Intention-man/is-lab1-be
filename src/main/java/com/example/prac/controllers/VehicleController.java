package com.example.prac.controllers;

import com.example.prac.DTO.data.VehicleDTO;
import com.example.prac.service.data.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO savedVehicle = vehicleService.save(vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleService.findAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable int id) {
        return vehicleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehiclePartially(@PathVariable int id, @RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO updatedVehicle = vehicleService.partialUpdate(id, vehicleDTO);
            return ResponseEntity.ok(updatedVehicle);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable int id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-by-fuel-consumption")
    public ResponseEntity<Void> deleteVehiclesByFuelConsumption(@RequestParam double fuelConsumption) {
        vehicleService.deleteVehiclesByFuelConsumption(fuelConsumption);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-one-by-fuel-consumption")
    public ResponseEntity<Void> deleteOneVehicleByFuelConsumption(@RequestParam double fuelConsumption) {
        vehicleService.deleteOneVehicleByFuelConsumption(fuelConsumption);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/unique-fuel-consumptions")
    public ResponseEntity<List<Double>> getUniqueFuelConsumptions() {
        return ResponseEntity.ok(vehicleService.getUniqueFuelConsumptions());
    }

    @PostMapping("/find-by-type")
    public ResponseEntity<List<Object[]>> findVehiclesByType(@RequestBody String type) {
        return ResponseEntity.ok(vehicleService.findVehiclesByType(type));
    }

    @PostMapping("/add-wheels/{vehicleId}")
    public ResponseEntity<Void> addWheelsToVehicle(@PathVariable int vehicleId, @RequestParam long additionalWheels) {
        vehicleService.addWheelsToVehicle(vehicleId, additionalWheels);
        return ResponseEntity.noContent().build();
    }

}
