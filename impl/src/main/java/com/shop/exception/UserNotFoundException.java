package com.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserNotFoundException extends BaseNotFoundException {

    private final Integer id;

    @Override
    public String getMessage() {
        return String.format("Order with id %d not found", getId());
    }
}
