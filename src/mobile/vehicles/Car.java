package mobile.vehicles;

import java.util.List;

public class Car extends Vehicle {
    public static final String HEATED_SEATS = "heated seats included";

    public Car() {
        super();
        this.optionalProperties.addAll(List.of(NB_DOORS, HEATED_SEATS));
    }
}