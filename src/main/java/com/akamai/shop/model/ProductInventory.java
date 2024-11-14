package com.akamai.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long onHandCount;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_variation_id", nullable = false)
    private ProductVariation productVariation;
}
