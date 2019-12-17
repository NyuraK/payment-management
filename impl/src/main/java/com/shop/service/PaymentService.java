package com.shop.service;

import com.shop.api.swagger.models.OrderDto;
import com.shop.api.swagger.models.PaymentDto;
import com.shop.api.swagger.models.Status;
import com.shop.client.OrderServiceClient;
import com.shop.client.OrderServiceSender;
import com.shop.model.Payment;
import com.shop.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class PaymentService {
    private PaymentRepository repository;
    private OrderServiceClient client;
    private OrderServiceSender orderSender;

    @Autowired
    public PaymentService(PaymentRepository repository, OrderServiceClient client, OrderServiceSender orderServiceSender) {
        this.repository = repository;
        this.client = client;
        this.orderSender = orderServiceSender;
    }

    public PaymentDto pay(OrderDto order) {
        Payment payment = new Payment();
        Double total = client.getTotalAmount(order.getId());
        payment.setCustomerId(order.getCustomerId());
        payment.setOrderId(order.getId());
        payment.setStatus(Status.OK);
        log.debug("Received total {}", total);
        repository.save(payment);
        orderSender.sendMessage(order.getId(), payment.getId());
        return convertToDTO(payment);
    }

    private PaymentDto convertToDTO(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.id(payment.getId());
        paymentDto.customerId(payment.getCustomerId());
        paymentDto.setOrderId(payment.getOrderId());
        return paymentDto;
    }

    public List<PaymentDto> getAllProducts(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(this::convertToDTO).collect(Collectors.toList());
    }
}