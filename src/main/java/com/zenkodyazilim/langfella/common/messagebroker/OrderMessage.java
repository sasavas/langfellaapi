package com.zenkodyazilim.langfella.common.messagebroker;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderMessage implements Serializable {
    private Long orderId;
    private String item;
    private int quantity;
}
