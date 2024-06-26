package com.hendisantika.springdatareactivemongodb.service;

import com.hendisantika.springdatareactivemongodb.document.Log;
import com.hendisantika.springdatareactivemongodb.document.LogLevel;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.MessageListener;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.TailableCursorRequest;

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
 * Time: 08:46
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
public class ErrorLogsCounter implements LogsCounter {

    private static final String LEVEL_FIELD_NAME = "level";

    private final String collectionName;
    private final MessageListenerContainer container;

    private final AtomicInteger counter = new AtomicInteger();

    public ErrorLogsCounter(MongoTemplate mongoTemplate,
                            String collectionName) {
        this.collectionName = collectionName;
        this.container = new DefaultMessageListenerContainer(mongoTemplate);

        container.start();
        TailableCursorRequest<Log> request = getTailableCursorRequest();
        container.register(request, Log.class);
    }

    @SuppressWarnings("unchecked")
    private TailableCursorRequest<Log> getTailableCursorRequest() {
        MessageListener<Document, Log> listener = message -> {
            log.info("ERROR log received: {}", message.getBody());
            counter.incrementAndGet();
        };

        return TailableCursorRequest.builder()
                .collection(collectionName)
                .filter(query(where(LEVEL_FIELD_NAME).is(LogLevel.ERROR)))
                .publishTo(listener)
                .build();
    }

    @Override
    public int count() {
        return counter.get();
    }

    @PreDestroy
    public void close() {
        container.stop();
    }
}
