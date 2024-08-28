package com.agoracharis.orderservice.data.response;

public record CustomerResp(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
