package hu.vibe.homework.hello.infrastructure;

import hu.vibe.homework.hello.domain.Greeting;
import hu.vibe.homework.hello.domain.GreetingRepository;
import hu.vibe.homework.hello.infrastructure.entity.GreetingEntity;
import hu.vibe.homework.hello.infrastructure.entity.GreetingMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@ApplicationScoped
public class GreetingPanacheRepository implements PanacheRepository<GreetingEntity>, GreetingRepository {

    @Inject
    EntityManager em;

    @Override
    public Optional<Greeting> findByName(String name) {
        return find("name", name).firstResultOptional().map(GreetingMapper::toDomain);
    }

    @Override
    public Greeting save(Greeting greeting) {
        GreetingEntity entity = GreetingMapper.toEntity(greeting);
        if (entity.getId() == null) {
            persist(entity);
            return GreetingMapper.toDomain(entity);
        } else {
            GreetingEntity updatedEntity = em.merge(entity);
            return GreetingMapper.toDomain(updatedEntity);
        }
    }
}
