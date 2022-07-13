package com.kjam.graphQL.resolvers;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Name;
import com.kjam.graphQL.entities.Teammate;
import com.kjam.graphQL.repositories.NameRepository;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TeamResolver implements GraphQLResolver<Teammate>{

    private final NameRepository repository;

    @Async("ResolverThreadPool") 
    public CompletableFuture<Name> name(Teammate teammate) {
        return repository.findById(teammate.getNintendoId()).toFuture();
    }
    
}
