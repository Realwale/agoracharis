package com.agoracharis.orderservice.data;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String state;
    private String city;
    private String street;
    private String houseNumber;
    private String zipCode;
}
