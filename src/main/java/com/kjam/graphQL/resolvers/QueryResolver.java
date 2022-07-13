package com.kjam.graphQL.resolvers;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Name;
import com.kjam.graphQL.entities.Teammate;
import com.kjam.graphQL.repositories.NameRepository;
import com.kjam.graphQL.repositories.TeamRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class QueryResolver implements GraphQLQueryResolver {

    private final NameRepository nameRepository;
    private final TeamRepository teamRepository;

    @Async("ResolverThreadPool")
    public CompletableFuture<Iterable<Teammate>> team(String teamId) {
        return CompletableFuture.completedFuture(teamRepository.findTeamByTeamId(teamId).toIterable());
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<Iterable<Teammate>> teammates(String nintendoId) {
        return CompletableFuture.completedFuture(teamRepository.findTeamByNintendoId(nintendoId).toIterable());
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<Name> name(String id) {
        return nameRepository.findById(id).toFuture();
    }
}