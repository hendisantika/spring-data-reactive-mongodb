package com.hendisantika.springdatareactivemongodb.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-data-reactive-mongodb-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-13
 * Time: 21:22
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@ToString(exclude = {"id", "dateOfBirth"})
@Document
public class Person {

    @Id
    private String id;
    private String firstName;
    private String secondName;
    private LocalDateTime dateOfBirth;
    private String profession;
    private int salary;

    public Person(
            final String firstName,
            final String secondName,
            final LocalDateTime dateOfBirth,
            final String profession,
            final int salary) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.profession = profession;
        this.salary = salary;
    }

}