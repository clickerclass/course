package com.clickerclass.course.config;

import com.clickerclass.course.handler.CourseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

    private final CourseHandler courseHandler;

    public RouterConfig(@Autowired CourseHandler courseHandler) {
        this.courseHandler = courseHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(GET("/api/course/findByClientIdOwner"), courseHandler::findByClientOwnerId)
                .andRoute(POST("/api/course"), courseHandler::save)
                .andRoute(DELETE("/api/course"), courseHandler::delete)
                .andRoute(GET("/api/course/findByClientId"), courseHandler::findByClientId)
                .andRoute(PUT("/api/course/checkIn"), courseHandler::checkIn)
                .andRoute(GET("/api/course/findByName"), courseHandler::findByName)
                .andRoute(GET("/api/course/findById"), courseHandler::findByName);
    }
}
