package com.shop.client;

import org.springframework.stereotype.Component;

@Component
public class OrderServiceClientFallback implements OrderServiceClient {

    @Override
    public Double getTotalAmount(String id) {
        return null;
    }
}