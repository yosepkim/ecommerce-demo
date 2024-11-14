package com.akamai.shop.service;

import com.akamai.shop.model.Product;
import com.akamai.shop.model.ProductInventory;
import com.akamai.shop.model.ProductVariation;
import com.akamai.shop.respository.ProductInventoryRepository;
import com.akamai.shop.respository.ProductRepository;
import com.akamai.shop.respository.ProductVariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DataSeedService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductVariationRepository productVariationRepository;
    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    public String seedProducts() {
        for (int i = 0; i < 10; i++) {
            Product product = Product.builder()
                    .name("Team " + (i + 1))
                    .description("Awesome shirt for Team " + (i + 1))
                    .build();

            productRepository.save(product);

            ProductVariation productVariation1 = ProductVariation.builder()
                    .name("S")
                    .description("Small")
                    .price(9.99)
                    .product(product)
                    .build();


            ProductVariation productVariation2 = ProductVariation.builder()
                    .name("M")
                    .description("Medium")
                    .price(19.99)
                    .product(product)
                    .build();


            ProductVariation productVariation3 = ProductVariation.builder()
                    .name("L")
                    .description("Large")
                    .price(29.99)
                    .product(product)
                    .build();


            productVariationRepository.saveAll(List.of(productVariation1, productVariation2, productVariation3));
            seedProductInventories(productVariation1);
            seedProductInventories(productVariation2);
            seedProductInventories(productVariation3);
        }
        return "completed";
    }

    private void seedProductInventories(ProductVariation productionVariation) {
        Random rand = new Random();
        ProductInventory productInventory = ProductInventory.builder()
                .productVariation(productionVariation)
                .onHandCount((long) (rand.nextInt((10) + 1)))
                .build();
        productInventoryRepository.save(productInventory);
    }
}
