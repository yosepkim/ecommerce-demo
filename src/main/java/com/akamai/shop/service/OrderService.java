package com.akamai.shop.service;

import com.akamai.shop.model.Order;
import com.akamai.shop.model.ProductInventory;
import com.akamai.shop.respository.OrderRepository;
import com.akamai.shop.respository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    public String placeOrder(Order order) {
        order.setStatus("PAID");
        orderRepository.save(order);
        updateInventoryCount(order);
        return "saved";
    }

    private String updateInventoryCount(Order order) {
        order.getLineItems().forEach(lineItem -> {
            ProductInventory inventory = productInventoryRepository.findById(lineItem.getProductVariation().getInventory().getId()).get();
            Long currentQuantity = inventory.getOnHandCount();
            Long newQuantity = currentQuantity - lineItem.getQuantity();
            inventory.setOnHandCount(newQuantity);
            productInventoryRepository.save(inventory);
        });
        return "successful";
    }
}
