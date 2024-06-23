package com.hendisantika.springdatareactivemongodb.repository;

import com.hendisantika.springdatareactivemongodb.ContainerBase;
import com.hendisantika.springdatareactivemongodb.document.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/23/24
 * Time: 09:06
 * To change this template use File | Settings | File Templates.
 */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringDataReactiveMongodbApplication.class)
//@DataMongoTest
//@Testcontainers
//@ContextConfiguration(classes = MongoDBTestContainerConfig.class)
@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountCrudRepositoryManualTest extends ContainerBase {

    @Autowired
    AccountCrudRepository repository;

    @Test
    public void givenValue_whenFindAllByValue_thenFindAccount() {
        repository.save(new Account(null, "Bill", 12.3)).block();
        Flux<Account> accountFlux = repository.findAllByValue(12.3);

        StepVerifier.create(accountFlux)
                .assertNext(account -> {
                    assertEquals("Bill", account.getOwner());
                    assertEquals(Double.valueOf(12.3), account.getValue());
                    assertNotNull(account.getId());
                })
                .expectComplete()
                .verify();
    }
}
