package com.shop.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OrderServiceClient {
    private RestTemplate restTemplate;

    @Autowired
    public OrderServiceClient(RestTemplate restTemplate) {

    }

    public void getTotalAmount(String id) {
        ResponseEntity<Double> response = restTemplate.getForEntity("http://localhost:8085/orderManagement/orders/"
                + id + "/total", Double.class);
    }
}
