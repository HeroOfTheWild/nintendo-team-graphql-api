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
@Table("NINTENDO.TEAMMATE")
public class Teammate {

    @Id
    @Column("TEAM_ID")
    private String teamId;

    @Column("NINTENDO_ID")    
    private String nintendoId;

    @Column("PRIMARY_TEAM")
    private String primary;
}
