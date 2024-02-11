package com.betfair.logistics.controller;

import com.betfair.logistics.dao.dto.AddOrderDto;
import com.betfair.logistics.dao.dto.OrderDto;
import com.betfair.logistics.exceptions.CannotCreateResourceException;
import com.betfair.logistics.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor //Lombok annotation to create a constructor with all required fields
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/add")
    public List<OrderDto> addOrders(@RequestBody @Valid List<AddOrderDto> ordersDtos) throws CannotCreateResourceException {
       return  orderService.addOrders(ordersDtos);
    }

    @PutMapping("/cancel")
    public void cancelOrders(@RequestBody List<Long> orderIds) {
        orderService.cancelOrders(orderIds);
    }

    @GetMapping("/status")
    public List<OrderDto> getOrders(@RequestParam(name="date",required = false,defaultValue = "") String dateAsString,
                                    @RequestParam(name="destination",required = false,defaultValue = "") String destinationQueryParam) {
        return orderService.getOrders(dateAsString, destinationQueryParam);
    }

}
