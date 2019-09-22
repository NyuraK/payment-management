package com.shop.model;

import com.shop.exception.ProductInBasketNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Basket {

    private Integer customer_id;

    private Map<Integer, Integer> listOfProducts;

    public void addProduct(Integer id) {
        if (listOfProducts.containsKey(id)) {
            listOfProducts.put(id, listOfProducts.get(id) + 1);
        }
        else {
            listOfProducts.put(id, 1);
        }
    }

    public void deleteProduct(Integer id) {
        if (listOfProducts.containsKey(id)) {
            if (listOfProducts.get(id) > 1) {
                listOfProducts.put(id, listOfProducts.get(id) - 1);
            }
            else {
                listOfProducts.remove(id);
            }
        }
        else {
            throw new ProductInBasketNotFoundException(id);
        }
    }
}
