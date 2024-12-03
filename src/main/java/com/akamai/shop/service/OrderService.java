package com.akamai.shop.service;

import com.akamai.shop.model.Order;
import com.akamai.shop.model.ProductVariation;
import com.akamai.shop.respository.OrderRepository;
import com.akamai.shop.respository.ProductVariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductVariationRepository productVariationRepository;

    public String placeOrder(Order order) {
        order.setStatus("PAID");
        orderRepository.save(order);
        updateInventoryCount(order);
        return "saved";
    }

    private String updateInventoryCount(Order order) {
        order.getLineItems().forEach(lineItem -> {
            ProductVariation productVariation = productVariationRepository.findById(lineItem.getProductVariation().getId()).get();
            Long currentQuantity = productVariation.getOnHandCount();
            Long newQuantity = currentQuantity - lineItem.getQuantity();
            productVariation.setOnHandCount(newQuantity);
            productVariationRepository.save(productVariation);
        });
        return "successful";
    }
}
