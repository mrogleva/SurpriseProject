package mobile.vehicles;

import java.util.List;

public class Truck extends Vehicle {
    public static final String TRAILER = "trailer included";

    public Truck() {
        super();
        this.optionalProperties.addAll(List.of(NB_DOORS, TRAILER));
    }
}