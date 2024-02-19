package com.betfair.logistics.controller;

import com.betfair.logistics.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping("/new-day")
    public String advancedDate(){
        return shippingService.advanceDate();
    }
}
