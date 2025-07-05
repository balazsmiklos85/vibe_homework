package hu.vibe.homework.hello.domain;

import java.util.Optional;

public interface GreetingRepository {
    Optional<Greeting> findByName(String name);
    Greeting save(Greeting greeting);
}
