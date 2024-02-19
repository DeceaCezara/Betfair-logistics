package com.betfair.logistics.service.runnable;

import com.betfair.logistics.dao.model.Destination;
import com.betfair.logistics.dao.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DelivaryTask implements Runnable {
    private final Destination destination;
    private final List<Order> orders;
    @SneakyThrows
    @Override
    public void run() {
    }
}
