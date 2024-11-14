package com.akamai.shop.respository;

import com.akamai.shop.model.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> { }
