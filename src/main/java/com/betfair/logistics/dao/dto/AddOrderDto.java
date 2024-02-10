package com.betfair.logistics.dao.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddOrderDto {

    @NotNull
    private Long delivaryDate;

    @NotNull
    private Long destinationId;


}
