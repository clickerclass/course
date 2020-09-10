package com.clickerclass.course.service;

import com.clickerclass.course.model.CourseModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseService {
    Flux<CourseModel> findByClientId(String id);

    Flux<CourseModel> findByClientOwnerId(String id);

    Mono<CourseModel> save(CourseModel courseModel);

    Mono<CourseModel> checkIn(String courseId, String password, String studentId);

    Flux<CourseModel> findAllByNameOrderByNameDesc(String name);

    Mono<CourseModel> findById(String id);

    Mono<Void> delete(String id);
}
