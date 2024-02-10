package com.betfair.logistics.dao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DestinationDto {

    private Long id;

    @NotBlank(message="Name is mandatory")
    private String name;

    @NotNull
    @Min(0)
    private int distance;


}
