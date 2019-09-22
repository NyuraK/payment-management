package com.shop.model;

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
public class User {

    @Id
    @GeneratedValue(generator = "increment")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Transient
    private Basket basket;

    public void addProductInBasket(Integer id) {
        basket.addProduct(id);
    }

    public void deleteProductFromBasket(Integer id) {
        basket.deleteProduct(id);
    }
}
