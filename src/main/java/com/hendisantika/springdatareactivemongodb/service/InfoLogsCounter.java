package com.hendisantika.springdatareactivemongodb.service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;

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

}
