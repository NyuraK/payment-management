package com.shop.service;

import com.shop.api.swagger.models.OrderDto;
import com.shop.api.swagger.models.PaymentDto;
import com.shop.api.swagger.models.Status;
import com.shop.model.Payment;
import com.shop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class PaymentService {
    private PaymentRepository repository;

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public PaymentDto pay(OrderDto order) {
        Payment payment = new Payment();
        payment.setCustomerId(order.getCustomerId());
        payment.setOrderId(order.getId());
        payment.setTotal(order.getTotal());
        payment.setStatus(Status.OK);
        return convertToDTO(payment);
    }

    private PaymentDto convertToDTO(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.id(payment.getId());
        paymentDto.customerId(payment.getCustomerId());
        paymentDto.orderId(payment.getOrderId());
        paymentDto.total(payment.getTotal());
        return paymentDto;
    }

    public List<PaymentDto> getAllProducts(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(this::convertToDTO).collect(Collectors.toList());
    }
}