package com.kjam.graphQL.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.kjam.graphQL.entities.Team;

public interface TeamRepository extends ReactiveCrudRepository<Team, String> {
    
}
