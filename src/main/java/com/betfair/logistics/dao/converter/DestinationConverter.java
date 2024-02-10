package com.betfair.logistics.dao.converter;

import com.betfair.logistics.dao.dto.DestinationDto;
import com.betfair.logistics.dao.model.Destination;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;


public class DestinationConverter {

    public static DestinationDto modelToDto(Destination destination) {
        return DestinationDto.builder()
                .id(destination.getId())
                .name(destination.getName())
                .distance(destination.getDistance())
                .build();
    }

    public static List<DestinationDto> modelListToDtoList(List<Destination> destinations) {
        return destinations.stream()
                .map(DestinationConverter::modelToDto)
                .toList();

    }

    public static Destination dtoToModel(DestinationDto destinationDto) {
        return Destination.builder()
                .id(destinationDto.getId())
                .name(destinationDto.getName())
                .distance(destinationDto.getDistance())
                .build();
    }

    public static List<Destination> dtoListToModelList(List<DestinationDto> destinationDtos) {
        return destinationDtos.stream()
                .map(DestinationConverter::dtoToModel)
                .toList();
    }




}
