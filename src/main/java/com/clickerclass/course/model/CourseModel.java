package com.clickerclass.course.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CourseModel {
    private String id;
    private String name;
    private String description;
    private String clientId;
    private String urlImage;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date creationDate;
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> students;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Date initialRegistrationDate;
    private Date endRegistrationDate;
}
