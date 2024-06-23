package com.hendisantika.springdatareactivemongodb.repository;

import com.hendisantika.springdatareactivemongodb.document.Log;
import com.hendisantika.springdatareactivemongodb.document.LogLevel;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/23/24
 * Time: 08:45
 * To change this template use File | Settings | File Templates.
 */
public interface LogsRepository extends ReactiveCrudRepository<Log, String> {
    @Tailable
    Flux<Log> findByLevel(LogLevel level);
}
