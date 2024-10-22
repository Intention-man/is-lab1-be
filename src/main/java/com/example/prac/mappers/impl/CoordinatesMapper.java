package com.example.prac.mappers.impl;

import com.example.prac.DTO.data.CoordinatesDTO;
import com.example.prac.mappers.Mapper;
import com.example.prac.model.dataEntity.Coordinates;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CoordinatesMapper implements Mapper<Coordinates, CoordinatesDTO> {
    private final ModelMapper modelMapper;

    public CoordinatesMapper() {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public CoordinatesDTO mapTo(Coordinates coordinates) {
        return modelMapper.map(coordinates, CoordinatesDTO.class);
    }

    @Override
    public Coordinates mapFrom(CoordinatesDTO coordinatesDTO) {
        return modelMapper.map(coordinatesDTO, Coordinates.class);
    }
}
