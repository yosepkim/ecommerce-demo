package com.akamai.shop.service;

import com.akamai.shop.model.Store;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HarperClient {

    private RestTemplate restTemplate;
    private final String harperDBUrl = "https://miami-edgecloud9.harperdbcloud.com:9925/";
    private final String harperDBAccessToken = "eWtpbTokSWxvdmVKZXN1czEyMyE=";
    public HarperClient() {
        restTemplate = new RestTemplate();
    }
    public void saveInventory(Store store, Long productVariationId, Long onHandCount) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + harperDBAccessToken);

        String query = "{\n" +
                "    \"operation\": \"insert\",\n" +
                "    \"database\": \"ecommerce\",\n" +
                "    \"table\": \"inventory\",\n" +
                "    \"records\": [\n" +
                "        {\n" +
                "           \"name\": \"" + store.getName() + "\",\n" +
                "           \"address\": \"" + store.getAddress() + "\",\n" +
                "           \"city\": \"" + store.getCity() + "\",\n" +
                "           \"state\": \"" + store.getState() + "\",\n" +
                "           \"zip\": \"" + store.getZip() + "\",\n" +
                "           \"latitude\": " + store.getLatitude() + ",\n" +
                "           \"longitude\": " + store.getLongitude() + ",\n" +
                "           \"productionVariationId\": " + productVariationId + ",\n" +
                "           \"onHandCount\": " + onHandCount + "\n" +
                "       }\n" +
                "   ]\n" +
        "}";

        HttpEntity<String> request = new HttpEntity<>(query, headers);

        restTemplate.postForEntity(harperDBUrl, request, String.class);
    }
}
