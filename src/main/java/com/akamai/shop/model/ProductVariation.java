package com.akamai.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;

    @JsonBackReference(value = "product")
    @ManyToOne
    private Product product;

    @OneToOne(mappedBy = "productVariation")
    private ProductInventory inventory;

    @JsonBackReference(value = "lineItems")
    @OneToMany(mappedBy = "productVariation")
    private List<LineItem> lineItems;
}
