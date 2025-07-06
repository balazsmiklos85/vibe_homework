package hu.vibe.homework.hello.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;

@Entity
public class GreetingEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Min(0)
    private long callCount;

    public GreetingEntity() {
    }

    public GreetingEntity(String name) {
        this.name = name;
        this.callCount = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCallCount() {
        return callCount;
    }

    public void setCallCount(long callCount) {
        this.callCount = callCount;
    }

    public void incrementCallCount() {
        this.callCount++;
    }
}
