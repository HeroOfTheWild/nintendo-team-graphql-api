package com.kjam.graphQL.inputs;

import com.kjam.graphQL.entities.Name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeammateInput {
    
    private String teamId;
    private Name name;
}
