package com.agoracharis.orderservice.config.client;

import com.agoracharis.orderservice.data.request.PurchaseRequest;
import com.agoracharis.orderservice.data.response.PurchaseResp;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Service
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResp> purchaseProduct(List<PurchaseRequest> requests){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> reqEntity = new HttpEntity<>(requests, httpHeaders);

        ParameterizedTypeReference<List<PurchaseResp>> reference =
                new ParameterizedTypeReference<>() {
        };

        ResponseEntity<List<PurchaseResp>> responseEntity = restTemplate.exchange(
                productUrl+"/purchase",
                POST,
                reqEntity,
                reference
                );
        if (responseEntity.getStatusCode().isError()){
            throw new BadRequestException("Ann error occurred "+ responseEntity.getStatusCode());
        }
            return responseEntity.getBody();
    }
}
