package com.kjam.graphQL.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Example;
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
public class MainQueryResolver implements GraphQLQueryResolver {

    private final NameRepository nameRepository;
    private final TeamRepository teamRepository;

    @Async("ResolverThreadPool")
    public CompletableFuture<Name> name(String nintendoId) {
        return nameRepository.findById(nintendoId).toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<Teammate> primaryTeam(String nintendoId) {
        var teammateExample = Example.of(Teammate.builder().nintendoId(nintendoId).primary("Y").build());
        return teamRepository.findOne(teammateExample).toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Teammate>> team(String teamId) {
        return teamRepository.findTeamByTeamId(teamId).collectList().toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Teammate>> teammates(String nintendoId) {
        return teamRepository.findTeamByNintendoId(nintendoId).collectList().toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Teammate>> myTeams(String nintendoId) {
        var teammateExample = Example.of(Teammate.builder().nintendoId(nintendoId).build());
        return teamRepository.findAll(teammateExample).collectList().toFuture();
    }
}
