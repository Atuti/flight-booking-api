package org.atuti.mokaya.booking.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "airlines")
@Setter
@Getter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirlineEntity {

    @Id
    private long airlineId;
    private String name;
    private String alias;
    private String iata;
    private String icao;
    private String callsign;
    private String countryName;
    private String active;

}
