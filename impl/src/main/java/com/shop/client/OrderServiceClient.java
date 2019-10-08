package com.shop.client;

import com.shop.exception.OrderServiceClientCommunicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.shop.config.RestTemplateConfig.LOCALHOST_URL;

@Component
@Slf4j
public class OrderServiceClient {
    @Value("${client.order.port}")
    private String port;
    @Value("${client.order.prefix}")
    private String prefix;

    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double getTotalAmount(String id) {
        final String url = String.format(LOCALHOST_URL, port, prefix);
        try {
            ResponseEntity<Double> response = restTemplate.getForEntity(url + "/orders/"
                    + id + "/total", Double.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new OrderServiceClientCommunicationException(e.getLocalizedMessage());
        }
    }
}
