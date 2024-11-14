package com.akamai.shop.controller;

import com.akamai.shop.model.Order;
import com.akamai.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping()
    public String saveOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }
}
