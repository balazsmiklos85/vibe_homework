package hu.vibe.homework.order.infrastructure.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderItemEntity {
    private String productCode;
    private int quantity;
    private BigDecimal unitPrice;
}
