package hu.vibe.homework.hello.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ORDERS")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
}
