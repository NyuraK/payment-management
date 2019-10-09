package com.shop.client;

import com.shop.api.swagger.models.PaidOrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderServiceSender {

    @Value("${rabbitmq.order.queue}")
    private String queue;

    @Value("${rabbitmq.order.routingKey}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderServiceSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String orderId, Integer paymentId) {
        PaidOrderMessage data = new PaidOrderMessage().orderId(orderId).paymentId(paymentId);
        log.info("Sending message to the queue using routingKey {}. Message= {}", rabbitTemplate.getRoutingKey(), data);
        rabbitTemplate.setQueue(queue);
        rabbitTemplate.setRoutingKey(routingKey);
        rabbitTemplate.convertAndSend(data);
        log.info("The message has been sent to the queue.");
    }
}
