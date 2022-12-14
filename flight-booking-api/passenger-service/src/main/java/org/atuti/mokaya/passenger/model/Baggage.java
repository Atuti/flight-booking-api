package org.atuti.mokaya.passenger.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Baggage {
    private Long id;
    private Double weightInKg;
    private Long bookingId;
}
