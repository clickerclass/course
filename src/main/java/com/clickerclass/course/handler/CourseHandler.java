package com.clickerclass.course.handler;

import com.clickerclass.course.exception.DuplicateException;
import com.clickerclass.course.model.CourseModel;
import com.clickerclass.course.model.RegistryModel;
import com.clickerclass.course.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class CourseHandler {

    private final CourseService courseService;

    public CourseHandler(CourseService courseService) {
        this.courseService = courseService;
    }

    public Mono<ServerResponse> findByClientOwnerId(ServerRequest request) {
        Optional<String> clientIdOptional = request.queryParam("clientId");
        if (clientIdOptional.isEmpty()) {
            return badRequest();
        }
        return courseService
                .findByClientOwnerId(clientIdOptional
                        .get())
                .collectList()
                .flatMap(courseModels -> ServerResponse
                        .ok()
                        .body(BodyInserters
                                .fromValue(courseModels)));
    }

    public Mono<ServerResponse> findByClientId(ServerRequest request) {
        Optional<String> clientIdOptional = request.queryParam("clientId");
        if (clientIdOptional.isEmpty()) {
            return badRequest();
        }
        return courseService
                .findByClientId(clientIdOptional
                        .get())
                .collectList()
                .flatMap(courseModels -> ServerResponse
                        .ok()
                        .body(BodyInserters
                                .fromValue(courseModels)));
    }

    public Mono<ServerResponse> findByName(ServerRequest request) {
        Optional<String> nameOptional = request.queryParam("name");
        if (nameOptional.isEmpty()) {
            return badRequest();
        }
        return this.courseService.
                findAllByNameOrderByNameDesc(nameOptional.get())
                .collectList()
                .flatMap(courseModels -> ServerResponse
                        .ok()
                        .body(BodyInserters.fromValue(courseModels)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        Optional<String> idOptional = request.queryParam("id");
        if (idOptional.isEmpty()) {
            return badRequest();
        }
        return courseService.findById(idOptional
                .get())
                .flatMap(courseModel -> ServerResponse
                        .ok()
                        .body(BodyInserters.fromValue(courseModel)));
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<CourseModel> courseModelMono = request.bodyToMono(CourseModel.class);
        return courseModelMono
                .flatMap(courseModel -> this.courseService
                        .save(courseModel)
                        .flatMap(courseModelResp -> ServerResponse
                                .ok()
                                .body(BodyInserters
                                        .fromValue(courseModelResp))));
    }

    public Mono<ServerResponse> checkIn(ServerRequest request) {
        Mono<RegistryModel> registryModelMono = request.bodyToMono(RegistryModel.class);
        return registryModelMono
                .flatMap(registryModel -> this.courseService
                        .checkIn(registryModel.getCourseId(),
                                registryModel.getPassword(),
                                registryModel.getStudentId())
                        .flatMap(courseModel -> ServerResponse
                                .ok()
                                .body(BodyInserters
                                        .fromValue(courseModel))))
                .onErrorResume(DuplicateException.class, throwable -> ServerResponse
                        .status(HttpStatus.CONFLICT)
                        .build())
                .switchIfEmpty(ServerResponse
                        .status(HttpStatus.FORBIDDEN)
                        .build());
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Optional<String> idOptional = request.queryParam("id");
        if (idOptional.isEmpty()) {
            return badRequest();
        }
        return this.courseService
                .delete(idOptional.get())
                .flatMap(aVoid -> ServerResponse
                        .ok()
                        .build());
    }

    private Mono<ServerResponse> badRequest() {
        return ServerResponse.badRequest().build();
    }
}
