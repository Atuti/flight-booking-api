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
@Table(name = "boarding_passes")
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardingPassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String qrCode;
    private Long bookingId;
    private String terminal;
    private String gate;
    private String scheduledTime;

}
