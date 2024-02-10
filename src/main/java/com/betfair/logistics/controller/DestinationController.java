package com.betfair.logistics.controller;

import com.betfair.logistics.dao.dto.DestinationDto;
import com.betfair.logistics.exceptions.CannotCreateResourceException;
import com.betfair.logistics.exceptions.ResourceNotFoundException;
import com.betfair.logistics.service.DestinationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @GetMapping
    public List<DestinationDto> getAllDestinations(){
        return destinationService.getAllDestinations();
    }

    @GetMapping("/{id}")
    public DestinationDto getDestination(@PathVariable Long id) throws ResourceNotFoundException {
        return destinationService.getDestinationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDestination(@PathVariable Long id) throws ResourceNotFoundException {
        destinationService.deleteDestination(id);
    }

    @PostMapping
    public Long  createDestination(@RequestBody @Valid DestinationDto destinationDto) throws CannotCreateResourceException {
       return destinationService.createDestination(destinationDto);
    }

    @PutMapping
    public void updateDestination(@RequestBody @Valid DestinationDto destinationDto) throws CannotCreateResourceException {
          destinationService.updateDestination(destinationDto);
    }


}
