package com.shop.model;

import com.shop.api.swagger.models.PaymentType;
import com.shop.api.swagger.models.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class Payment {

    @Id
    @GeneratedValue(generator = "increment")
    private Integer id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "customer_id")
    private String customerId;

    @Column
    private Double total;

    @Column
    private Status status;

    @Column
    private PaymentType paymentType;
}