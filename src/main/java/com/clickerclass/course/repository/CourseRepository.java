package com.clickerclass.course.repository;

import com.clickerclass.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CourseRepository extends ReactiveCrudRepository<Course, String> {

    Flux<Course> findByClientId(String id);

    Flux<Course> findByStudents(String student);

    Mono<Course> findByIdAndAndPassword(String id, String password);

    Flux<Course> findByNameLikeIgnoreCase(String name);

}
