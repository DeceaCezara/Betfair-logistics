package com.betfair.logistics.dao.repository;

import com.betfair.logistics.dao.model.Order;
import com.betfair.logistics.dao.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByDestinationId(Long destinationId);
    default void archiveOrder(Order order) {
        order.setDestination(null);
        order.setOrderStatus(OrderStatus.ARCHIVED);
        this.save(order);
    }


}
