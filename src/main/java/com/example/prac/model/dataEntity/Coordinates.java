package com.example.prac.model.dataEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Data;

@Entity
@Data
@Table(name="coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Max(value = 625)
    @Column(nullable = false)
    private Long x;

    @Column(nullable = false)
    private Long y;
}