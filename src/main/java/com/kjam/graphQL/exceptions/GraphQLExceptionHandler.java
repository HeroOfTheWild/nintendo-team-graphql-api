package com.kjam.graphQL.exceptions;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import graphql.kickstart.spring.error.ThrowableGraphQLError;

@Component
public class GraphQLExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ThrowableGraphQLError handle(DataNotFoundException e) {
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler({RuntimeException.class, IllegalArgumentException.class, Exception.class})
    public ThrowableGraphQLError handle(Exception e) {
        return new ThrowableGraphQLError(e);
    }
}
