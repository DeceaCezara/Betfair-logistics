package com.betfair.logistics.service;

import com.betfair.logistics.config.CompanyInfo;
import com.betfair.logistics.dao.converter.OrderConverter;
import com.betfair.logistics.dao.dto.AddOrderDto;
import com.betfair.logistics.dao.dto.OrderDto;
import com.betfair.logistics.dao.model.Destination;
import com.betfair.logistics.dao.model.Order;
import com.betfair.logistics.dao.model.OrderStatus;
import com.betfair.logistics.dao.repository.DestinationRepository;
import com.betfair.logistics.dao.repository.OrderRepository;
import com.betfair.logistics.exceptions.CannotCreateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CompanyInfo companyInfo;

    private final DestinationRepository destinationRepository;

    private final OrderRepository orderRepository;
    public List<OrderDto> getOrders(String dateAsString, String destinationQueryParam) {
        Long dateAsLong=companyInfo.getCurrentDateAsLong();

        if(!dateAsString.isBlank())
            dateAsLong= companyInfo.getLocalDateStringAsLong(dateAsString);

       List<Order> foundOrders= orderRepository.findAllByDeliveryDateAndDestination_NameContainingIgnoreCase(dateAsLong,destinationQueryParam);

       return OrderConverter.modelListToDtoList(foundOrders);
    }

    public List<OrderDto> addOrders(List<AddOrderDto> addOrdersDtos) throws CannotCreateResourceException {

        Map<Long,Destination> destinationMap=destinationRepository.findAll().stream()
                        .collect(Collectors.toMap(Destination::getId, Function.identity()));

        validateOrdersPayload(addOrdersDtos,destinationMap.keySet());

        List<Order> ordersToSave=new ArrayList<>();
        for (AddOrderDto addOrderDto : addOrdersDtos) {
            ordersToSave.add(
                    createNewOrder(addOrderDto.getDelivaryDate(), destinationMap.get(addOrderDto.getDestinationId())));
        }

        return OrderConverter.modelListToDtoList(orderRepository.saveAll(ordersToSave));


    }

    public void cancelOrders(List<Long> orderIds) {

        List<Order> foundOrders=orderRepository.findAllById(orderIds);

        for(Order order:foundOrders){
            if(order.getOrderStatus() !=OrderStatus.DELIVERED )
                order.setOrderStatus(OrderStatus.CANCELLED);
        }

        //nu face batch update
        orderRepository.saveAll(foundOrders); //daca cade una cad toate

    }

    private void validateOrdersPayload(List<AddOrderDto> ordersDtos, Set<Long> destinationIds) throws CannotCreateResourceException {
        StringBuilder errors= new StringBuilder();
        for (int i = 0; i < ordersDtos.size(); i++) {
            AddOrderDto orderDto = ordersDtos.get(i);
            if (!destinationIds.contains(orderDto.getDestinationId())) {
                errors.append(String.format("Destination with id %s does not exist for order %d\n", orderDto.getDestinationId(), i));
            }
            if(companyInfo.getCurrentDateAsLong() >= orderDto.getDelivaryDate()){
                errors.append(String.format("Delivery date %s is in the past for order %d\n", orderDto.getDelivaryDate(), i));
            }
        }
        if(!errors.isEmpty()){
            throw new CannotCreateResourceException(errors.toString());
        }
    }

    private Order createNewOrder(Long deliveryDate,Destination destination) {
        Order order=Order.builder()
                .orderStatus(OrderStatus.NEW)
                .lastUpdated(System.currentTimeMillis())
                .deliveryDate(deliveryDate)
                .destination(destination)
                .build();
        return order;
    }
}
