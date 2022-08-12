package org.atuti.mokaya.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Airline {
    private long airlineId;
    private String name;
    private String alias;
    private  String icao;
    private String iata;
    private String callsign;
    private Country country;
    private String active;
}
