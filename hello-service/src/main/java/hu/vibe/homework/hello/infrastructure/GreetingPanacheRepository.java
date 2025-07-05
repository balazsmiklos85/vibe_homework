package hu.vibe.homework.hello.infrastructure;

import hu.vibe.homework.hello.domain.Greeting;
import hu.vibe.homework.hello.domain.GreetingRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class GreetingPanacheRepository implements PanacheRepository<Greeting>, GreetingRepository {
    @Override
    public Optional<Greeting> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
