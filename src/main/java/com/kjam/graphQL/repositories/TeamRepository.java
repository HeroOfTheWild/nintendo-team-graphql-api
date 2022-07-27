package com.kjam.graphQL.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.kjam.graphQL.entities.Teammate;

import reactor.core.publisher.Flux;

public interface TeamRepository extends ReactiveCrudRepository<Teammate, String> , ReactiveQueryByExampleExecutor<Teammate> {
    @Query("SELECT TEAM_ID, NINTENDO_ID, TEAM_NM, MANAGER_ID FROM NINTENDO.TEAM WHERE TEAM_ID IN (SELECT TEAM_ID FROM NINTENDO.TEAM WHERE NINTENDO_ID = $1) AND NINTENDO_ID != $1")
    Flux<Teammate> findTeamByNintendoId(String nintendoId);

    @Query("SELECT TEAM_ID, NINTENDO_ID, TEAM_NM, MANAGER_ID FROM NINTENDO.TEAM WHERE TEAM_ID = $1")
    Flux<Teammate> findTeamByTeamId(String teamId);
}
