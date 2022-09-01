package com.kjam.graphQL.repositories;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;

import com.kjam.graphQL.configurations.TestDatabaseConfiguration;

@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
@DataR2dbcTest
public class RepositoryTest {

    @Autowired
    TeammateRepository repository;

    @Autowired
    NameRepository nameRepo;

    @Test
    public void retrieveTeammatesByTeamId() {
        var teamIterable = repository.findTeamByTeamId("nintendo01").toIterable();
        var teammates = StreamSupport.stream(teamIterable.spliterator(), false).collect(Collectors.toList());
        Assertions.assertEquals(4, teammates.size());
        teammates.forEach(teammate -> Assertions.assertEquals("nintendo01", teammate.getTeamId()));
    }

    @Test
    public void retrieveTeammatesByNintendoId() {
        var nintendoId = "nin0001";
        var teamIterable = repository.findTeamByNintendoId(nintendoId).toIterable();
        var teammates = StreamSupport.stream(teamIterable.spliterator(), false).collect(Collectors.toList());
        Assertions.assertEquals(3, teammates.size());
        teammates.forEach(teammate -> {
            Assertions.assertEquals("nintendo01", teammate.getTeamId());
            Assertions.assertNotEquals(nintendoId, teammate.getNintendoId());
        });
    }

    @Test
    public void whenTeammateIsOnMultipleTeams_thenReturnMembersFromBothTeams() {
        var nintendoId = "nin9999";
        var teamIterable = repository.findTeamByNintendoId(nintendoId).toIterable();
        var teammates = StreamSupport.stream(teamIterable.spliterator(), false).collect(Collectors.toList());
        Assertions.assertEquals(11, teammates.size());
        teammates.forEach(teammate -> {
            var teamId = teammate.getTeamId();
            Assertions.assertTrue("nintendo01".equalsIgnoreCase(teamId) || "nintendo02".equalsIgnoreCase(teamId) || "nintendo03".equalsIgnoreCase(teamId));
            Assertions.assertNotEquals(nintendoId, teammate.getNintendoId());
        });
    }

    @Test
    public void returnsEmptyListOfTeammatesIfNoTeammates_IncludingSelf_areNotOnFile() {
        var teamIterable = repository.findTeamByNintendoId("nintendoId").toIterable();
        var teammates = StreamSupport.stream(teamIterable.spliterator(), false).collect(Collectors.toList());
        Assertions.assertTrue(teammates.isEmpty());
    }

    @Test
    public void retrieveNameByNintendoId() {
        var name = nameRepo.findById("nin0001").block();
        Assertions.assertNotNull(name);
        Assertions.assertEquals("nin0001", name.getNintendoId());
        Assertions.assertEquals("Mario", name.getFirstName());
        Assertions.assertEquals("Jumpman", name.getMiddleName());
        Assertions.assertEquals("Nintendo", name.getLastName());
    }

    @Test
    public void returnNullNameWhenNoEntryForNintendoId() {
        var name = nameRepo.findById("nin0000").block();
        Assertions.assertNull(name);
    }
    
}
