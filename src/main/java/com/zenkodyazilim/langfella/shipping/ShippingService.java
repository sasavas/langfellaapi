package com.zenkodyazilim.langfella.shipping;

import com.zenkodyazilim.langfella.common.messagebroker.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {
    private static final Logger logger = LoggerFactory.getLogger(ShippingService.class);

    @RabbitListener(queues = "order.queue")
    public void shipOrder(OrderMessage orderMessage) {
        logger.info("Shipping order log: {}", orderMessage);
    }
}