package com.kjam.graphQL.resolvers;

import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Name;
import com.kjam.graphQL.entities.Team;
import com.kjam.graphQL.entities.Teammate;
import com.kjam.graphQL.exceptions.DataNotFoundException;
import com.kjam.graphQL.inputs.TeammateInput;
import com.kjam.graphQL.repositories.NameRepository;
import com.kjam.graphQL.repositories.TeamRepository;
import com.kjam.graphQL.repositories.TeammateRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class MutationResolver implements GraphQLMutationResolver {
    
    private final NameRepository        nameRepository;
    private final TeamRepository        teamRepository;
    private final TeammateRepository    teammateRepository;

    private static int base = 1000;

    public String newTeam(Team newTeam) {
        System.out.println("NINTENDO " + newTeam.toString());
        return null;
    }

    public Teammate newTeammate(TeammateInput newTeammate) {
        var teamId = newTeammate.getTeamId();
        var team = teamRepository.findById(teamId).blockOptional();
        var nintendoId = newNintendoId();

        team.ifPresentOrElse((t) -> {
            var name = newTeammate.getName();
    
            issue(name, nintendoId);
            issue(newTeammate.getTeamId(), nintendoId);
        }, () -> {
            base-=1;
            throw new DataNotFoundException(String.format("Sorry, but the team %s DOES NOT EXIST", teamId));
        });
        
        return Teammate.builder().nintendoId(nintendoId).teamId(teamId).primary("Y").build();
    }

    private void issue(Name name, String nintendoId) {
        nameRepository.insert(nintendoId, name.getFirstName(), name.getMiddleName(), name.getLastName()).block();
    }

    private void issue(String teamId, String nintendoId) {
        teammateRepository.insert(nintendoId, teamId).block();
    }

    private String newNintendoId() {
        var nintendoId = "nin" + base;
        base += 1;
        if(base%10==0) throw new RuntimeException("IDK WHAT HAPPENED");
        return nintendoId;
    }
}
