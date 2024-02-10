package com.betfair.logistics.dao.dto;

import com.betfair.logistics.dao.model.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

    private Long id;
    private Long deliveryDate;
    private Long lastUpdated;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Long destinationId;
}
