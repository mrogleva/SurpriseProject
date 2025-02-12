package mobile.vehicles;

import java.util.List;

public class Car extends Vehicle {

    public Car() {
        super();
        this.mandatoryProperties.addAll(List.of("nbOfDoors"));
    }
}