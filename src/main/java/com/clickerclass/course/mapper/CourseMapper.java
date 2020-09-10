package com.clickerclass.course.mapper;

import com.clickerclass.course.entity.Course;
import com.clickerclass.course.model.CourseModel;
import com.clickerclass.course.util.Encryption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseModel toModel(Course course);

    @Mapping(source = "password", target = "password", qualifiedByName = "Encrypting")
    Course toEntity(CourseModel courseModel);

    @Named("Encrypting")
    default String encrypting(String value) {

        return Encryption.encrypting(value);
    }
}
