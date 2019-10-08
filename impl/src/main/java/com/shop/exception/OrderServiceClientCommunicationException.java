package com.shop.exception;

public class OrderServiceClientCommunicationException extends BaseNotFoundException {

    private final String message;

    public OrderServiceClientCommunicationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
