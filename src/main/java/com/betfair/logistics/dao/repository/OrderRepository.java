package com.betfair.logistics.dao.repository;

import com.betfair.logistics.dao.model.Order;
import com.betfair.logistics.dao.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static com.betfair.logistics.dao.model.OrderStatus.*;
import static java.util.Objects.isNull;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByDestinationId(Long destinationId);

    List<Order> findAllByDeliveryDate(Long deliveryDate);
    //pentru a cauta dupa un field intr-un field
    List<Order> findAllByDeliveryDateAndDestination_NameContainingIgnoreCase(Long deliveryDate, String destinationQueryString);
    default void archiveOrder(Order order) {
        order.setDestination(null);
        this.changeOrderState(order, ARCHIVED);
        this.save(order);
    }
    default boolean changeOrderState(Order order, OrderStatus newStatus){
        OrderStatus currentStatus = order.getOrderStatus();

        if(OrderStatus.allowedTransitions.get(currentStatus).contains(newStatus)){
            order.setOrderStatus(newStatus);
            return true;
        }
        return false;
//        switch (newStatus) {
//            case NEW -> {
//                if (currentStatus == CANCELLED)
//                    order.setOrderStatus(NEW);
//            }
//            case DELIVERING -> {
//                if (currentStatus == NEW)
//                    order.setOrderStatus(OrderStatus.DELIVERING);
//            }
//            case DELIVERED -> {
//                if (currentStatus == DELIVERING)
//                    order.setOrderStatus(OrderStatus.DELIVERED);
//            }
//            case CANCELLED -> {
//                if (currentStatus != DELIVERED)
//                    order.setOrderStatus(CANCELLED);
//            }
//            case ARCHIVED -> order.setOrderStatus(ARCHIVED);
//            default -> throw new IllegalArgumentException("Invalid order status");
//        }
    }

    default int updateStatusForOrders(List<Long> orderIds,OrderStatus initialStatus, OrderStatus newStatus){
        int affectedRows=0;

        List<Order> ordersToUpdate =this.findAllById(orderIds).stream()
                .filter(order -> isNull(initialStatus) || order.getOrderStatus().equals(initialStatus))
                .toList();

       for(Order order:ordersToUpdate) {
           if (this.changeOrderState(order, newStatus)) {
               affectedRows++;
           }
       }
        this.saveAll(ordersToUpdate);

           return affectedRows;
    }





}
