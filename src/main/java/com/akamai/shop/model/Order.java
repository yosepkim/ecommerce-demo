package com.akamai.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalAmount;
    private Double taxAmount;
    private Double grandTotalAmount;
    private String status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<LineItem> lineItems;
}
