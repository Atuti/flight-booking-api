package org.atuti.mokaya.passenger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties
public class Passenger {

    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String citizenship;
    private String residence;
    private String passportNumber;
    private Baggage baggage;
}
