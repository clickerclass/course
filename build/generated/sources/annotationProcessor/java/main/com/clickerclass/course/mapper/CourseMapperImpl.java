package com.clickerclass.course.mapper;

import com.clickerclass.course.entity.Course;
import com.clickerclass.course.model.CourseModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-08T21:12:28-0500",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15-ea (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseModel toModel(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseModel courseModel = new CourseModel();

        courseModel.setId( course.getId() );
        courseModel.setName( course.getName() );
        courseModel.setDescription( course.getDescription() );
        courseModel.setClientId( course.getClientId() );
        courseModel.setUrlImage( course.getUrlImage() );
        courseModel.setCreationDate( course.getCreationDate() );
        List<String> list = course.getStudents();
        if ( list != null ) {
            courseModel.setStudents( new ArrayList<String>( list ) );
        }
        courseModel.setPassword( course.getPassword() );
        courseModel.setInitialRegistrationDate( course.getInitialRegistrationDate() );
        courseModel.setEndRegistrationDate( course.getEndRegistrationDate() );

        return courseModel;
    }

    @Override
    public Course toEntity(CourseModel courseModel) {
        if ( courseModel == null ) {
            return null;
        }

        Course course = new Course();

        course.setPassword( encrypting( courseModel.getPassword() ) );
        course.setId( courseModel.getId() );
        course.setName( courseModel.getName() );
        course.setDescription( courseModel.getDescription() );
        course.setClientId( courseModel.getClientId() );
        course.setUrlImage( courseModel.getUrlImage() );
        course.setCreationDate( courseModel.getCreationDate() );
        List<String> list = courseModel.getStudents();
        if ( list != null ) {
            course.setStudents( new ArrayList<String>( list ) );
        }
        course.setInitialRegistrationDate( courseModel.getInitialRegistrationDate() );
        course.setEndRegistrationDate( courseModel.getEndRegistrationDate() );

        return course;
    }
}
