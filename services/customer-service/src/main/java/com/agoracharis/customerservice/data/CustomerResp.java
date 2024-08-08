package com.agoracharis.customerservice.data;

import com.agoracharis.customerservice.model.Address;

public record CustomerResp(
                           String id,
                           String firstName,
                           String lastName,
                           String email,
                           Address address
) {
}
