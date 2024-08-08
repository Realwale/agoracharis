package com.agoracharis.customerservice.model;


import com.agoracharis.customerservice.common.BaseEntity;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends BaseEntity {

    private String state;
    private String city;
    private String street;
    private String houseNumber;
    private String zipCode;
}
