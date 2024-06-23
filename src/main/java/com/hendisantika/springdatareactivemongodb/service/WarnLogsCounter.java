package com.hendisantika.springdatareactivemongodb.service;

import com.hendisantika.springdatareactivemongodb.document.Log;
import com.hendisantika.springdatareactivemongodb.document.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/23/24
 * Time: 08:48
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class WarnLogsCounter implements LogsCounter {

    private static final String LEVEL_FIELD_NAME = "level";

    private final AtomicInteger counter = new AtomicInteger();
    private final Disposable subscription;

    public WarnLogsCounter(ReactiveMongoOperations template) {
        Flux<Log> stream = template.tail(query(where(LEVEL_FIELD_NAME).is(LogLevel.WARN)), Log.class);
        subscription = stream.subscribe(logEntity -> {
            log.warn("WARN log received: " + logEntity);
            counter.incrementAndGet();
        });
    }

    @Override
    public int count() {
        return counter.get();
    }

}
