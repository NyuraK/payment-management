package com.shop.controller;

import com.shop.api.swagger.controllers.PaymentManagementApi;
import com.shop.api.swagger.models.OrderDto;
import com.shop.api.swagger.models.PaymentDto;
import com.shop.service.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@Api(tags = "Payment management")
public class PaymentController implements PaymentManagementApi {
    private PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<PaymentDto> pay(OrderDto orderDto) {
        Objects.requireNonNull(orderDto);
        return ResponseEntity.ok(service.pay(orderDto));
    }

    @Override
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        return ResponseEntity.ok(service.getAllProducts());
    }
}
