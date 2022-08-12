package org.atuti.mokaya.booking.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Airplane {
    private long id;
    private String name;
    private String iataCode;
    private String icaoCode;
}
