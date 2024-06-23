package com.hendisantika.springdatareactivemongodb.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/23/24
 * Time: 08:40
 * To change this template use File | Settings | File Templates.
 */
@Document
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    private String id;
    private String owner;
    private Double value;
}
