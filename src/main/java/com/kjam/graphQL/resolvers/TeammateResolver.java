package com.kjam.graphQL.resolvers;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Name;
import com.kjam.graphQL.entities.Team;
import com.kjam.graphQL.entities.Teammate;
import com.kjam.graphQL.repositories.NameRepository;
import com.kjam.graphQL.repositories.TeamRepository;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TeammateResolver implements GraphQLResolver<Teammate>{

    private final NameRepository nameRepository;
    private final TeamRepository teamRepository;

    @Async("ResolverThreadPool") 
    public CompletableFuture<Name> name(Teammate teammate) {
        return nameRepository.findById(teammate.getNintendoId()).toFuture();
    }

    @Async("ResolverThreadPool") 
    public CompletableFuture<Team> teamInfo(Teammate teammate) {
        return teamRepository.findById(teammate.getTeamId()).toFuture();
    }
    
}
