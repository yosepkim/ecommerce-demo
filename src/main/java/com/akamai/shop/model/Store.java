package com.akamai.shop.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Store {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private Double longitude;
    private Double latitude;

}
