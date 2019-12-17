package com.shop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order-management")
public interface OrderServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/orders/{id}/total")
    Double getTotalAmount(@PathVariable String id);
}
