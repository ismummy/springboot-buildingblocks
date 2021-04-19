package com.ismummy.restservices.controllers;

import com.ismummy.restservices.entities.Order;
import com.ismummy.restservices.exceptions.UserNotFoundException;
import com.ismummy.restservices.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}/orders")
    public List<Order> getAllOrders(@Valid @PathVariable @Min(1) Long userId) throws UserNotFoundException {
        return orderService.getAllOrders(userId);
    }

    @PostMapping("/{userId}/orders")
    public Order createOrder(@Valid @RequestBody Order order, @PathVariable @Min(1) Long userId) throws UserNotFoundException {
        return orderService.createOrder(order, userId);
    }
}
