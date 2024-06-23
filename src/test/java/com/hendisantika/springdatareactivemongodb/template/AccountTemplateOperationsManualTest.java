package com.hendisantika.springdatareactivemongodb.template;

import com.hendisantika.springdatareactivemongodb.document.Account;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/23/24
 * Time: 09:52
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountTemplateOperationsManualTest {

    @Autowired
    AccountTemplateOperations accountTemplate;

    @Test
    public void givenAccount_whenSave_thenSave() {
        Account account = accountTemplate.save(Mono.just(new Account(null, "Raul", 12.3))).block();
        assertNotNull(account.getId());
    }

}
