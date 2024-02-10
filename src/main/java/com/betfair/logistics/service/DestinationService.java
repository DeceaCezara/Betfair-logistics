package com.betfair.logistics.service;

import com.betfair.logistics.dao.converter.DestinationConverter;
import com.betfair.logistics.dao.dto.DestinationDto;
import com.betfair.logistics.dao.model.Destination;
import com.betfair.logistics.dao.repository.DestinationRepository;
import com.betfair.logistics.dao.repository.OrderRepository;
import com.betfair.logistics.exceptions.CannotCreateResourceException;
import com.betfair.logistics.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final OrderRepository orderRepository;

    public List<DestinationDto> getAllDestinations() {
        List<Destination>destinationList =destinationRepository.findAll();
        return DestinationConverter.modelListToDtoList(destinationList);
    }

    public DestinationDto getDestinationById(Long id) throws ResourceNotFoundException {
        Destination destination= destinationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Destination not found"));

        return DestinationConverter.modelToDto(destination);
    }

    public void deleteDestination(Long id) throws ResourceNotFoundException {
        Destination destination= destinationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Destination not found"));
        orderRepository.findAllByDestinationId(id).forEach(orderRepository::archiveOrder);
        destinationRepository.delete(destination);
    }

    public Long createDestination(@RequestBody @Valid DestinationDto destinationDto) throws CannotCreateResourceException {
        //VALIDARE+CREARE

        if(destinationDto.getId()!=null){
            throw new CannotCreateResourceException("Id must be null");
        }

        if(destinationRepository.findByName(destinationDto.getName()).isPresent()){
            throw new CannotCreateResourceException(String.format("Destination with name %s already exists",destinationDto.getName()));
        }

        Destination destination= DestinationConverter.dtoToModel(destinationDto);
        destinationRepository.save(destination);

        return destination.getId();
    }

    public void updateDestination(DestinationDto destinationDto) throws CannotCreateResourceException {
        if(destinationDto.getId()==null){
            throw new CannotCreateResourceException("Id should be provided");
        }

        Optional<Destination> destinationOptional=destinationRepository.findByName(destinationDto.getName());
        if(destinationOptional.isPresent()){
            Destination destination=destinationOptional.get();
            if(!Objects.equals(destination.getId(),destinationDto.getId())){
                throw new CannotCreateResourceException(String.format("Name=%s is already assigned to destination with id=%d",destination.getName(),destination.getId()));
            }
        }

        Destination destination= DestinationConverter.dtoToModel(destinationDto);
        destinationRepository.save(destination);

    }
}
