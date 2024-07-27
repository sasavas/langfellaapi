package com.zenkodyazilim.langfella.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
class OrderController {
    private final OrderRepository orderRepository;
    private final CheckoutService checkoutService;

    @Autowired
    public OrderController(OrderRepository orderRepository, CheckoutService checkoutService) {
        this.orderRepository = orderRepository;
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        checkoutService.placeOrder(savedOrder);
        return ResponseEntity
                .status(200)
                .body(savedOrder);
    }
}