package com.hendisantika.springdatareactivemongodb.service;

import com.hendisantika.springdatareactivemongodb.document.Log;
import com.hendisantika.springdatareactivemongodb.document.LogLevel;
import com.hendisantika.springdatareactivemongodb.repository.LogsRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/23/24
 * Time: 08:47
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class InfoLogsCounter implements LogsCounter {

    private final AtomicInteger counter = new AtomicInteger();
    private final Disposable subscription;

    public InfoLogsCounter(LogsRepository repository) {
        Flux<Log> stream = repository.findByLevel(LogLevel.INFO);
        this.subscription = stream.subscribe(logEntity -> {
            log.info("INFO log received: " + logEntity);
            counter.incrementAndGet();
        });
    }

    @Override
    public int count() {
        return this.counter.get();
    }
}
