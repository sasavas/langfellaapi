package com.zenkodyazilim.langfella.inventory;

import com.zenkodyazilim.langfella.common.exceptions.DomainValidationException;
import com.zenkodyazilim.langfella.common.exceptions.EntityNotFoundException;
import com.zenkodyazilim.langfella.common.messagebroker.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class InventoryServiceImpl implements InventoryService {
    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
    private final InventoryRepository inventoryRepository;

    @Autowired
    InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public boolean isAvailable(String item, int quantity) {
        Inventory inventory = inventoryRepository.findByItem(item).orElse(null);
        return inventory != null && inventory.getStock() >= quantity;
    }

    @RabbitListener(queues = "order.queue")
    public void handleOrderMessage(OrderMessage orderMessage) {
        logger.info("Received order message: {}", orderMessage);
        Inventory inventory = inventoryRepository
                .findByItem(orderMessage.getItem())
                .orElseThrow(() -> new EntityNotFoundException("order", orderMessage.getOrderId().toString()));
        if (inventory.getStock() >= orderMessage.getQuantity()) {
            inventory.setStock(inventory.getStock() - orderMessage.getQuantity());
            inventoryRepository.save(inventory);
            logger.info("Inventory updated for item: {}", orderMessage.getItem());
        } else {
            logger.error("Insufficient stock for item: {}", orderMessage.getItem());
            throw new DomainValidationException(Inventory.class.getSimpleName(), "Insufficient stock");
        }
    }
}