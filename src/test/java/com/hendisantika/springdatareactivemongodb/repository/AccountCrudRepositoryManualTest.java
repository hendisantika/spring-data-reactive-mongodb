package com.hendisantika.springdatareactivemongodb.repository;

import com.hendisantika.springdatareactivemongodb.SpringDataReactiveMongodbApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringDataReactiveMongodbApplication.class)
public class AccountCrudRepositoryManualTest {

    @Autowired
    AccountCrudRepository repository;


}
