package com.example.prac.service.data;

import com.example.prac.DTO.data.CoordinatesDTO;
import com.example.prac.mappers.impl.CoordinatesMapper;
import com.example.prac.model.dataEntity.Coordinates;
import com.example.prac.repository.data.CoordinatesRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;
    private final CoordinatesMapper coordinatesMapper;

    public CoordinatesDTO save(CoordinatesDTO coordinatesDTO) {
        Coordinates


                coordinates = coordinatesMapper.mapFrom(coordinatesDTO);
        return coordinatesMapper.mapTo(coordinatesRepository.save(coordinates));
    }

    public List<CoordinatesDTO> findAllCoordinates() {
        return coordinatesRepository.findAll().stream()
                .map(coordinatesMapper::mapTo).toList();
    }

    public Optional<CoordinatesDTO> findById(Long coordinatesId) {
        Optional<Coordinates> optionalCoordinates = coordinatesRepository.findById(Math.toIntExact(coordinatesId));
        return optionalCoordinates.map(coordinatesMapper::mapTo);
    }

    public boolean isExists(Long coordinatesId) {
        return coordinatesRepository.existsById(Math.toIntExact(coordinatesId));
    }

    public CoordinatesDTO partialUpdate(Long coordinatesId, CoordinatesDTO coordinatesDTO) {
        coordinatesDTO.setId(coordinatesId);
        return coordinatesRepository.findById(Math.toIntExact(coordinatesId)).map(existingCoordinates -> {
            CoordinatesDTO existingCoordinatesDto = coordinatesMapper.mapTo(existingCoordinates);
            Optional.ofNullable(coordinatesDTO.getX()).filter(x -> x <= 625).ifPresent(existingCoordinatesDto::setX);
            Optional.ofNullable(coordinatesDTO.getY()).ifPresent(existingCoordinatesDto::setY);
            return coordinatesMapper.mapTo(coordinatesRepository.save(coordinatesMapper.mapFrom(existingCoordinatesDto)));
        }).orElseThrow(() -> new RuntimeException("Coordinates don't exist"));
    }

    public void delete(Long coordinatesId) {
        coordinatesRepository.deleteById(Math.toIntExact(coordinatesId));
    }
}
