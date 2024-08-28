package com.agoracharis.orderservice.data.request;


import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineReq {
    private Integer orderId;
    private Integer productId;
    private int quantity;
}
