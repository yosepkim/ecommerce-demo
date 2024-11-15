package com.akamai.shop.service;

import com.akamai.shop.model.Product;
import com.akamai.shop.model.ProductInventory;
import com.akamai.shop.model.ProductVariation;
import com.akamai.shop.model.Store;
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

    @Autowired
    private HarperClient harperClient;

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

    private void seedProductInventories(ProductVariation productVariation) {
        Random rand = new Random();
        Long onHandCount = (long) rand.nextInt((10) + 1);
        ProductInventory productInventory = ProductInventory.builder()
                .productVariation(productVariation)
                .onHandCount(onHandCount)
                .build();
        productInventoryRepository.save(productInventory);
        seedHarper(productVariation, onHandCount);
    }

    private void seedHarper(ProductVariation variation, Long count) {
        Random rand = new Random();
        Long inventoryCount = count;
        List<Store> stores = getStores();

        Long onHandCount = (long) rand.nextInt((inventoryCount.intValue()) + 1);
        harperClient.saveInventory(stores.get(0), variation.getId(), onHandCount);

        inventoryCount = inventoryCount - onHandCount;

        onHandCount = (long) rand.nextInt((inventoryCount.intValue()) + 1);
        harperClient.saveInventory(stores.get(1), variation.getId(), onHandCount);

        inventoryCount = inventoryCount - onHandCount;
        harperClient.saveInventory(stores.get(2), variation.getId(), inventoryCount);
    }



    private List<Store> getStores() {
        Store store1 = Store.builder()
                .name("New Balance Mall of Georgia")
                .address("3260 Buford Dr #70")
                .city("Buford")
                .state("GA")
                .zip("30519")
                .latitude(34.06687652532365)
                .longitude(-83.98843674459813)
                .build();
        Store store2 = Store.builder()
                .name("New Balance Atlanta @ Toco Hills")
                .address("1975 Clairmont Rd suite A")
                .city("Decatur")
                .state("GA")
                .zip("30033")
                .latitude(33.83491625907735)
                .longitude(-84.30751137115023)
                .build();
        Store store3 = Store.builder()
                .name("New Balance Factory Store Woodstock")
                .address("15 Ridgewalk Pkwy Unit E-595")
                .city("Woodstock")
                .state("GA")
                .zip("30188")
                .latitude(34.14664356847463)
                .longitude(-84.52449027061677)
                .build();

        return List.of(store1, store2, store3);
    }
}
