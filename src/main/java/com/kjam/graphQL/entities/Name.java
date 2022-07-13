package com.kjam.graphQL.entities;

import java.sql.Timestamp;

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
@Table("NINTENDO.NAME")
public class Name {

    @Id
    @Column("NINTENDO_ID")
    private String nintendoId;

    @Column("FIRST_NM")
    private String firstName;
    
    @Column("MIDDLE_NM")
    private String middleName;

    @Column("LAST_NM")
    private String lastName;

    @Column("MODIFIED")
    private Timestamp modified;
    
}
