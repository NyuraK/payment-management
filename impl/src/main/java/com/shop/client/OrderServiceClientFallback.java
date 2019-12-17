package com.shop.client;

import com.shop.exception.OrderServiceUnavailableException;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceClientFallback implements OrderServiceClient {

    @Override
    public Double getTotalAmount(String id) {
        throw new OrderServiceUnavailableException();
    }
}
