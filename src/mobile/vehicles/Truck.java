package mobile.vehicles;

import java.util.List;

public class Truck extends Vehicle {

    public Truck() {
        super();
        this.mandatoryProperties.addAll(List.of("nbOfDoors"));
    }
}