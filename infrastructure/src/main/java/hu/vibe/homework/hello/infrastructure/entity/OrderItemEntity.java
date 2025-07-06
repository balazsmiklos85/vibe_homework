package hu.vibe.homework.hello.infrastructure.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderItemEntity {
    private String productCode;
    private int quantity;
    private double unitPrice;
}
