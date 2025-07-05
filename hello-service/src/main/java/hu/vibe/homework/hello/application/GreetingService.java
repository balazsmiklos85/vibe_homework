package hu.vibe.homework.hello.application;

import hu.vibe.homework.hello.domain.Greeting;
import hu.vibe.homework.hello.domain.GreetingPort;
import hu.vibe.homework.hello.domain.GreetingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GreetingService implements GreetingPort {

    private static final String GREETING_NAME = "hello";

    @Inject
    GreetingRepository greetingRepository;

    @Override
    @Transactional
    public String getGreeting() {
        Greeting greeting = greetingRepository.findByName(GREETING_NAME)
                .orElseGet(() -> {
                    Greeting newGreeting = new Greeting(GREETING_NAME);
                    greetingRepository.persist(newGreeting);
                    return newGreeting;
                });

        greeting.incrementCallCount();

        return String.format("Hello from Hexagonal Architecture! You are the %d. visitor.", greeting.getCallCount());
    }
}
