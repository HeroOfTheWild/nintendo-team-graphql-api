package com.kjam.graphQL.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.kjam.graphQL.entities.Teammate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeammateRepository extends ReactiveCrudRepository<Teammate, String> , ReactiveQueryByExampleExecutor<Teammate> {
    @Query("SELECT TEAM_ID, NINTENDO_ID FROM NINTENDO.TEAMMATE WHERE TEAM_ID IN (SELECT TEAM_ID FROM NINTENDO.TEAMMATE WHERE NINTENDO_ID = $1) AND NINTENDO_ID != $1")
    Flux<Teammate> findTeamByNintendoId(String nintendoId);

    @Query("SELECT TEAM_ID, NINTENDO_ID FROM NINTENDO.TEAMMATE WHERE TEAM_ID = $1")
    Flux<Teammate> findTeamByTeamId(String teamId);

    @Query("INSERT INTO NINTENDO.TEAMMATE (NINTENDO_ID, TEAM_ID) VALUES ($1, $2)")
    Mono<Void> insert(String nintendoId, String teamId);

}
