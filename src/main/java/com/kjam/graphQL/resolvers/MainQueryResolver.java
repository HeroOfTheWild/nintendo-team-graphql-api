package com.kjam.graphQL.resolvers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Name;
import com.kjam.graphQL.entities.Team;
import com.kjam.graphQL.entities.Teammate;
import com.kjam.graphQL.repositories.NameRepository;
import com.kjam.graphQL.repositories.TeamRepository;
import com.kjam.graphQL.repositories.TeammateRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class MainQueryResolver implements GraphQLQueryResolver {

    private final NameRepository nameRepository;
    private final TeamRepository teamRepository;
    private final TeammateRepository teammateRepository;

    @Async("ResolverThreadPool")
    public CompletableFuture<Name> myName(String nintendoId) {
        return nameRepository.findById(nintendoId).toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<Teammate> myNintendoAccount(String nintendoId) {
        var teammateExample = Example.of(Teammate.builder().nintendoId(nintendoId).primary("Y").build());
        return teammateRepository.findOne(teammateExample).toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Teammate>> myTeammates(String nintendoId) {
        return teammateRepository.findTeamByNintendoId(nintendoId).collectList().toFuture();
    }

    @Async("ResolverThreadPool") 
    public CompletableFuture<Team> myPrimaryTeam(String nintendoId) throws InterruptedException, ExecutionException {
        var teammate = myNintendoAccount(nintendoId).get();
        if(teammate != null) {
            return teamRepository.findById(teammate.getTeamId()).toFuture();
        }
        return null;
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Team>> myTeams(String nintendoId) throws InterruptedException, ExecutionException {
        var future = teammateRepository.findAll(Example.of(Teammate.builder().nintendoId(nintendoId).build())).collectList().toFuture();
        var teamIds = future.get().stream().map(x -> x.getTeamId()).collect(Collectors.toList());
        return teamRepository.findAllById(teamIds).collectList().toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<Team> team(String teamId) {
        return teamRepository.findById(teamId).toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Teammate>> teammates(String teamId) {
        return teammateRepository.findTeamByTeamId(teamId).collectList().toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Team>> teams() {
        return teamRepository.findAll().collectList().toFuture();
    }
}
