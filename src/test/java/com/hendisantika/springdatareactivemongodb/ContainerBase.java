package com.hendisantika.springdatareactivemongodb;

import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.lifecycle.Startables;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/23/24
 * Time: 09:18
 * To change this template use File | Settings | File Templates.
 */
public abstract class ContainerBase {

    static final GenericContainer mongo;

    static {
        mongo = new GenericContainer("mongo:8.0-rc-jammy")
                .withExposedPorts(27017)
                .waitingFor(Wait.forLogMessage(".*Waiting for connections.*\\n", 1))
                .withEnv("MONGO_INITDB_ROOT_USERNAME", "yuji")
                .withReuse(true)
                .withEnv("MONGO_INITDB_ROOT_PASSWORD", "S3cret");
    }

    static {
        Startables.deepStart(mongo).join();
    }

    @DynamicPropertySource
    static void mongoProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", mongo::getHost);
        registry.add("spring.data.mongodb.port", mongo::getFirstMappedPort);
        registry.add("spring.data.mongodb.database", () -> "app_db");
        registry.add("spring.data.mongodb.username", () -> "yuji");
        registry.add("spring.data.mongodb.password", () -> "S3cret");
    }

}
