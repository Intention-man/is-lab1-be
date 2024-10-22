package com.example.prac.service.data;

import com.example.prac.DTO.data.VehicleDTO;
import com.example.prac.mappers.impl.VehicleMapper;
import com.example.prac.model.dataEntity.Vehicle;
import com.example.prac.repository.data.CoordinatesRepository;
import com.example.prac.repository.data.VehicleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final VehicleMapper vehicleMapper;

    @Autowired
    private final SessionFactory sessionFactory;

    public VehicleDTO save(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.mapFrom(vehicleDTO);
        vehicle.setCreationDate(ZonedDateTime.now());

        if (!coordinatesRepository.existsById(Math.toIntExact(vehicleDTO.getCoordinatesId()))) {
            throw new RuntimeException("Coordinates not found");
        }

        return vehicleMapper.mapTo(vehicleRepository.save(vehicle));
    }

    public List<VehicleDTO> findAllVehicles() {
        return vehicleRepository.findAll().stream().map(vehicleMapper::mapTo).toList();
    }

    public Optional<VehicleDTO> findById(int vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        return optionalVehicle.map(vehicleMapper::mapTo);
    }

    public boolean isExists(int vehicleId) {
        return vehicleRepository.existsById(vehicleId);
    }

    public VehicleDTO partialUpdate(int vehicleId, VehicleDTO vehicleDTO) {
        vehicleDTO.setId(vehicleId);
        return vehicleRepository.findById(vehicleId).map(existingVehicle -> {
            VehicleDTO existingVehicleDto = vehicleMapper.mapTo(existingVehicle);
            Optional.ofNullable(vehicleDTO.getName()).ifPresent(existingVehicleDto::setName);
            Optional.ofNullable(vehicleDTO.getCoordinatesId()).ifPresent(coordinatesId -> {
                if (!coordinatesRepository.existsById(Math.toIntExact(coordinatesId))) {
                    throw new RuntimeException("Coordinates not found");
                }
                existingVehicleDto.setCoordinatesId(coordinatesId);
            });
            Optional.ofNullable(vehicleDTO.getType()).ifPresent(existingVehicleDto::setType);
            Optional.ofNullable(vehicleDTO.getEnginePower()).filter(ep -> ep > 0).ifPresent(existingVehicleDto::setEnginePower);
            Optional.of(vehicleDTO.getNumberOfWheels()).filter(nw -> nw > 0).ifPresent(existingVehicleDto::setNumberOfWheels);
            Optional.of(vehicleDTO.getCapacity()).filter(c -> c > 0).ifPresent(existingVehicleDto::setCapacity);
            Optional.ofNullable(vehicleDTO.getDistanceTravelled()).filter(dt -> dt > 0).ifPresent(existingVehicleDto::setDistanceTravelled);
            Optional.ofNullable(vehicleDTO.getFuelConsumption()).filter(fc -> fc > 0).ifPresent(existingVehicleDto::setFuelConsumption);
            Optional.ofNullable(vehicleDTO.getFuelType()).ifPresent(existingVehicleDto::setFuelType);
            return vehicleMapper.mapTo(vehicleRepository.save(vehicleMapper.mapFrom(existingVehicleDto)));
        }).orElseThrow(() -> new RuntimeException("Vehicle doesn't exist"));
    }

    public void delete(int vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }



    public void deleteVehiclesByFuelConsumption(double targetFuelConsumption) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("SELECT * FROM delete_vehicles_by_fuel_consumption(:targetFuelConsumption)")
                    .setParameter("targetFuelConsumption", targetFuelConsumption)
                    .getSingleResult();
            session.getTransaction().commit();
        }
    }

    public void deleteOneVehicleByFuelConsumption(double targetFuelConsumption) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("SELECT * FROM delete_one_vehicle_by_fuel_consumption(:targetFuelConsumption)")
                    .setParameter("targetFuelConsumption", targetFuelConsumption)
                    .getSingleResult();
            session.getTransaction().commit();
        }
    }

    public List<Double> getUniqueFuelConsumptions() {
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery("SELECT * FROM unique_fuel_consumptions()", Double.class).list();
        }
    }

    public List<Object[]> findVehiclesByType(String type) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createNativeQuery("SELECT * FROM find_vehicles_by_type(:type)", Object[].class)
                    .setParameter("type", type)
                    .list();
        }
    }

    public void addWheelsToVehicle(int vehicleId, long additionalWheels) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("SELECT * FROM add_wheels_to_vehicle(:vehicleId, :additionalWheels)")
                    .setParameter("vehicleId", vehicleId)
                    .setParameter("additionalWheels", additionalWheels)
                    .getSingleResult();
            session.getTransaction().commit();
        }
    }
}