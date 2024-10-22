package com.example.prac.repository.data;

import com.example.prac.model.dataEntity.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
}

