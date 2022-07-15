package com.kjam.graphQL.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.kjam.graphQL.entities.Name;

public interface NameRepository extends ReactiveCrudRepository<Name, String> { }
