package com.kjam.graphQL.exceptions;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import graphql.GraphqlErrorException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

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

    @ExceptionHandler({CoercingParseValueException.class, CoercingSerializeException.class, CoercingParseLiteralException.class})
    public ThrowableGraphQLError handle(GraphqlErrorException e) {
        return new ThrowableGraphQLError(e);
    }
}
