package com.shop.exception;

public class OrderServiceUnavailableException extends BaseNotFoundException {
    @Override
    public String getMessage() {
        return "Order service is not available";
    }
}
