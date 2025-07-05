package hu.vibe.homework.hello.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.Optional;

public interface GreetingRepository extends PanacheRepository<Greeting> {
    Optional<Greeting> findByName(String name);
}
