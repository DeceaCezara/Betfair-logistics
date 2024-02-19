package com.betfair.logistics.dao.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OrderStatus {
    NEW,DELIVERING,DELIVERED,CANCELLED,ARCHIVED;

    public static Map<OrderStatus, List<OrderStatus>> allowedTransitions=new HashMap<>();

    static{
        allowedTransitions.put(NEW, List.of(DELIVERING,CANCELLED,ARCHIVED));
        allowedTransitions.put(DELIVERING, List.of(DELIVERED,CANCELLED,ARCHIVED));
        allowedTransitions.put(DELIVERED, List.of(ARCHIVED));
        allowedTransitions.put(CANCELLED, List.of(NEW));
        allowedTransitions.put(ARCHIVED, List.of());
    }
}
