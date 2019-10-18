package com.shop.client;

import com.shop.api.swagger.models.PaidOrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderServiceSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderServiceSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String orderId, Integer paymentId) {
        PaidOrderMessage data = new PaidOrderMessage().orderId(orderId).paymentId(paymentId);
        log.info("Sending message to the queue using routingKey {}. Message= {}", rabbitTemplate.getRoutingKey(), data);
        rabbitTemplate.convertAndSend(data);
        log.info("The message has been sent to the queue.");
    }
}
