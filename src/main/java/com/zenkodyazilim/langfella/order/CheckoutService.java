package com.zenkodyazilim.langfella.order;

import com.zenkodyazilim.langfella.common.exceptions.LangfellaValidationException;
import com.zenkodyazilim.langfella.common.messagebroker.OrderMessage;
import com.zenkodyazilim.langfella.inventory.InventoryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CheckoutService {
    private final RabbitTemplate rabbitTemplate;
    private final InventoryService inventoryService;

    @Autowired
    CheckoutService(RabbitTemplate rabbitTemplate, InventoryService inventoryService) {
        this.rabbitTemplate = rabbitTemplate;
        this.inventoryService = inventoryService;
    }

    public void placeOrder(Order order) {
        if (!inventoryService.isAvailable(order.getItem(), order.getQuantity())) {
            throw new LangfellaValidationException(Order.class.getSimpleName(), "Insufficient stock for item: " + order.getItem());
        }

        rabbitTemplate.convertAndSend(
                "order.exchange",
                "order.routingkey",
                new OrderMessage(order.getId(), order.getItem(), order.getQuantity()));
    }
}