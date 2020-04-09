package com.hendisantika.springdatareactivemongodb.repository;

import com.hendisantika.springdatareactivemongodb.document.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

    Flux<Person> findByFirstName(final String firstName);

    Mono<Person> findOneByFirstName(final String firstName);
}