package org.atuti.mokaya.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {
    private long id;

    private Airline airline;
    private Airport sourceAirport;
    private Airport destinationAirport;
    private String codeshare;
    private String stops;
    private Airplane airplane;
}
