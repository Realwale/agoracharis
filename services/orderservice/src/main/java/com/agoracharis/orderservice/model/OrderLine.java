package com.agoracharis.orderservice.model;


import jakarta.persistence.*;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Order order;
    private Integer productId;
    private int quantity;
}
