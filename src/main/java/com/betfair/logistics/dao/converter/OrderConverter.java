package com.betfair.logistics.dao.converter;

import com.betfair.logistics.dao.dto.OrderDto;
import com.betfair.logistics.dao.model.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderConverter {

    public static OrderDto modelToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .deliveryDate(order.getDeliveryDate())
                .lastUpdated(order.getLastUpdated())
                .orderStatus(order.getOrderStatus())
                .destinationId(order.getDestination().getId())
                .build();
    }

    public static List<OrderDto> modelListToDtoList( List<Order> orders) {
        return orders.stream()
                .map(OrderConverter::modelToDto)
                .toList();
    }



}
