package com.agoracharis.customerservice.model;


import com.agoracharis.customerservice.common.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
