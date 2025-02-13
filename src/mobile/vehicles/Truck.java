package mobile.vehicles;

import java.util.Map;

public class Truck extends Vehicle {
    // Optional properties
    public static final String TRAILER = "hasTrailer";

    public Truck() {
        super();
        this.optionalProperties.putAll(Map.of(
                NB_DOORS, Integer.class,
                TRAILER, Boolean.class
        ));
    }
}