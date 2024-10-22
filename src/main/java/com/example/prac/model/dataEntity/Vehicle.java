package com.example.prac.model.dataEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
@Table(name="vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "ID must be greater than 0")
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be null or blank")
    private String name;

    @ManyToOne(optional = false)
    @NotNull(message = "Coordinates cannot be null")
    @JoinColumn(name = "coordinates_id", referencedColumnName = "id", nullable = false)
    private Coordinates coordinates;

    @NotNull(message = "Creation date cannot be null")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    private ZonedDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Min(value = 1, message = "Engine power must be greater than 0")
    @Column(name = "engine_power")
    private Integer enginePower;

    @Min(value = 1, message = "Number of wheels must be greater than 0")
    @NotNull(message = "Number of wheels cannot be null")
    @Column(name = "number_of_wheels")
    private long numberOfWheels;

    @Min(value = 1, message = "Capacity must be greater than 0")
    @NotNull(message = "Capacity cannot be null")
    private long capacity;

    @Min(value = 1, message = "Distance travelled must be greater than 0")
    @Column(name = "distance_travelled")
    private Long distanceTravelled;

    @DecimalMin(value = "0.1", inclusive = false, message = "Fuel consumption must be greater than 0")
    @Column(name = "fuel_consumption")
    private Double fuelConsumption;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

}
