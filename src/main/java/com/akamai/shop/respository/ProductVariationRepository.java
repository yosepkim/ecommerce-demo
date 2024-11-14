package com.akamai.shop.respository;

import com.akamai.shop.model.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariationRepository extends JpaRepository<ProductVariation, Long> { }
