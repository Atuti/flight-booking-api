package org.atuti.mokaya.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Table(name = "routes")
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String airlineICAO;
    private Long airlineId;
    private String sourceAirportIATA;
    private Long sourceAirportId;
    private String destinationAirportIATA;
    private Long destinationAirportId;
    private String codeshare;
    private String stops;
    private String airplaneCode;
    
}
