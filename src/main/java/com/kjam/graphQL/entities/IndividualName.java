package com.kjam.graphQL.entities;

import java.time.ZonedDateTime;

public record IndividualName(String id, String firstName,
                             String middleName, String lastName,
                             ZonedDateTime lastModified) { }
    

