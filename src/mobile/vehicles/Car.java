package mobile.vehicles;

import java.util.Map;

public class Car extends Vehicle {
    // Optional properties
    public static final String HEATED_SEATS = "heatedSeats";

    public Car() {
        super();
        this.optionalProperties.putAll(Map.of(
                NB_DOORS, Integer.class,
                HEATED_SEATS, Boolean.class
        ));
    }
}