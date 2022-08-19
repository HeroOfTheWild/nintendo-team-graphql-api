package com.kjam.graphQL.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("NINTENDO.TEAM")
public class Team {
    
    @Id
    @Column("TEAM_ID")
    private String teamId;

    @Column("TEAM_NM")
    private String teamName;

    @Column("MANAGER_ID")
    private String managerId;
}
