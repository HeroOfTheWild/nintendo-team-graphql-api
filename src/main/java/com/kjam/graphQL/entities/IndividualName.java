package com.kjam.graphQL.entities;

import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndividualName {

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private ZonedDateTime lastModified;
    
}
