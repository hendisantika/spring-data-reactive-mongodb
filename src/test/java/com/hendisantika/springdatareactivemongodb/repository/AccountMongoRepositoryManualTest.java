package com.hendisantika.springdatareactivemongodb.repository;

import com.hendisantika.springdatareactivemongodb.document.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/23/24
 * Time: 09:33
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Disabled
public class AccountMongoRepositoryManualTest {

    @Autowired
    AccountMongoRepository repository;

    @Test
    public void givenExample_whenFindAllWithExample_thenFindAllMatch() {
        repository.save(new Account(null, "Gojo", 12.3)).block();
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("Gojo", startsWith());
        Example<Account> example = Example.of(new Account(null, "Gojo", null), matcher);
        Flux<Account> accountFlux = repository.findAll(example);

        StepVerifier
                .create(accountFlux)
                .assertNext(account -> assertEquals("Gojo", account.getOwner()))
                .expectComplete()
                .verify();
    }

    @Test
    public void givenAccount_whenSave_thenSave() {
        Mono<Account> accountMono = repository.save(new Account(null, "john", 12.3));

        StepVerifier
                .create(accountMono)
                .assertNext(account -> assertNotNull(account.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void givenId_whenFindById_thenFindAccount() {
        Account inserted = repository.save(new Account(null, "john", 12.3)).block();
        Mono<Account> accountMono = repository.findById(inserted.getId());

        StepVerifier
                .create(accountMono)
                .assertNext(account -> {
                    assertEquals("john", account.getOwner());
                    assertEquals(Double.valueOf(12.3), account.getValue());
                    assertNotNull(account.getId());
                })
                .expectComplete()
                .verify();
    }
}
