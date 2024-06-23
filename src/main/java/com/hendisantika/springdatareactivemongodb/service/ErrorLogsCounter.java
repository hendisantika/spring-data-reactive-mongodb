package com.hendisantika.springdatareactivemongodb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;

import java.util.concurrent.atomic.AtomicInteger;

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

}
