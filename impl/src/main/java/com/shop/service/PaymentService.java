package com.shop.service;

import com.shop.api.swagger.models.OrderDto;
import com.shop.api.swagger.models.PaymentDto;
import com.shop.api.swagger.models.PaymentType;
import com.shop.api.swagger.models.Status;
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

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public PaymentDto pay(OrderDto order) {
        Payment payment = new Payment();
        payment.setCustomerId(order.getCustomerId());
        payment.setOrderId(order.getId());
        payment.setStatus(Status.OK);
        payment.setPaymentType(PaymentType.CARD_ONLINE);
        repository.save(payment);
        return convertToDTO(payment);
    }

    private PaymentDto convertToDTO(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.id(payment.getId());
        paymentDto.customerId(payment.getCustomerId());
        paymentDto.setOrderId(payment.getOrderId());
        return paymentDto;
    }

    public List<PaymentDto> getAllProducts() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(this::convertToDTO).collect(Collectors.toList());
    }
}