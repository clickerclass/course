package com.clickerclass.course.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("course")
@Data
public class Course {
    @Id
    private String id;
    private String name;
    private String description;
    private String clientId;
    private String urlImage;
    private Date creationDate;
    private List<String> students;
    private String password;
    private Date initialRegistrationDate;
    private Date endRegistrationDate;
}
