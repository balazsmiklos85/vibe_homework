package hu.vibe.homework.hello.infrastructure.entity;

import hu.vibe.homework.hello.domain.Greeting;

public class GreetingMapper {

    public static Greeting toDomain(GreetingEntity entity) {
        if (entity == null) {
            return null;
        }
        Greeting domain = new Greeting();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCallCount(entity.getCallCount());
        return domain;
    }

    public static GreetingEntity toEntity(Greeting domain) {
        if (domain == null) {
            return null;
        }
        GreetingEntity entity = new GreetingEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCallCount(domain.getCallCount());
        return entity;
    }
}
