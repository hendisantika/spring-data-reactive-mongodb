package com.hendisantika.springdatareactivemongodb.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/23/24
 * Time: 08:44
 * To change this template use File | Settings | File Templates.
 */
@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    private String id;
    private String service;
    private LogLevel level;
    private String message;
}
