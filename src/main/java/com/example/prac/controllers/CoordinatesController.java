package com.example.prac.controllers;

import com.example.prac.DTO.data.CoordinatesDTO;
import com.example.prac.service.data.CoordinatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/coordinates")
@AllArgsConstructor
public class CoordinatesController {
    private final CoordinatesService coordinatesService;

    @PostMapping
    public ResponseEntity<CoordinatesDTO> createCoordinates(@RequestBody CoordinatesDTO coordinatesDTO) {
        CoordinatesDTO savedCoordinates = coordinatesService.save(coordinatesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoordinates);
    }

    @GetMapping
    public ResponseEntity<List<CoordinatesDTO>> getAllCoordinates() {
        List<CoordinatesDTO> coordinates = coordinatesService.findAllCoordinates();
        return ResponseEntity.ok(coordinates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordinatesDTO> getCoordinatesById(@PathVariable Long id) {
        return coordinatesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoordinates(@PathVariable Long id) {
        coordinatesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}