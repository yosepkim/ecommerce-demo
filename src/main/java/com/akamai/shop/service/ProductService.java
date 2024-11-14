package com.akamai.shop.service;

import com.akamai.shop.model.Product;
import com.akamai.shop.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }
}
