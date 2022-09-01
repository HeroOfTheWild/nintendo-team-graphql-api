package com.kjam.graphQL.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.kjam.graphQL.entities.Name;

import reactor.core.publisher.Mono;

public interface NameRepository extends ReactiveCrudRepository<Name, String> {

    @Query("INSERT INTO NINTENDO.NAME (NINTENDO_ID, FIRST_NM, MIDDLE_NM, LAST_NM) VALUES ($1, $2, $3, $4)")
    public Mono<Void> insert(String nintendoId, String firstName, String middleName, String lastName);
}
