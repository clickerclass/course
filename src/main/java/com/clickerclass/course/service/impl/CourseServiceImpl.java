package com.clickerclass.course.service.impl;

import com.clickerclass.course.entity.Course;
import com.clickerclass.course.exception.DuplicateException;
import com.clickerclass.course.mapper.CourseMapper;
import com.clickerclass.course.model.CourseModel;
import com.clickerclass.course.repository.CourseRepository;
import com.clickerclass.course.service.CourseService;
import com.clickerclass.course.util.Encryption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(@Autowired CourseRepository courseRepository, @Autowired CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public Flux<CourseModel> findByClientId(String id) {
        return this.courseRepository.findByStudents(id).map(courseMapper::toModel);
    }

    @Override
    public Flux<CourseModel> findByClientOwnerId(String id) {
        return this.courseRepository.findByClientId(id).map(courseMapper::toModel);
    }

    @Override
    public Mono<CourseModel> save(CourseModel courseModel) {
        courseModel.setCreationDate(new Date());
        return this.courseRepository.save(courseMapper.toEntity(courseModel)).map(courseMapper::toModel);
    }

    @Override
    public Mono<CourseModel> checkIn(String courseId, String password, String studentId) {
        Mono<Course> courseMono = this.courseRepository.findByIdAndAndPassword(courseId,
                Encryption.encrypting(password));
        return courseMono.flatMap(course -> {
            List<String> students = course.getStudents();
            if (Objects.isNull(students)) {
                students = new ArrayList<>();
            }
            if (students.stream().anyMatch(s -> s.equals(studentId))) {
                return Mono.error(() -> {
                    throw new DuplicateException("Student already registered");
                });
            }
            students.add(studentId);
            course.setStudents(students);
            return courseRepository.save(course).map(courseMapper::toModel);
        });
    }

    @Override
    public Flux<CourseModel> findAllByNameOrderByNameDesc(String name) {

        return this.courseRepository.findByNameLikeIgnoreCase(name).map(courseMapper::toModel);
    }

    @Override
    public Mono<CourseModel> findById(String id) {
        return this.courseRepository.findById(id).map(courseMapper::toModel);
    }

    @Override
    public Mono<Void> delete(String id) {
        return this.courseRepository.deleteById(id);
    }
}
