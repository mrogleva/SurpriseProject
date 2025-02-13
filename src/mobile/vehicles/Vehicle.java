package mobile.vehicles;

import mobile.listings.ListingCategory;

import java.util.*;

public abstract class Vehicle {
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String COLOR = "color";
    public static final String YEAR = "year";
    public static final String CONDITION = "condition";

    public static final String MANDATORY_PROPERTIES = BRAND + "/" + MODEL + "/" + COLOR + "/" + YEAR + "/" + CONDITION;

    public static final String NB_DOORS = "nbOfDoors";
    public static final String FUEL_TYPE = "fuelType";

    private final Map<String, Object> properties;

    protected final Map<String, Class<?>> mandatoryProperties;
    protected final Map<String, Class<?>> optionalProperties;

    public static Vehicle getVehicleFromListingCategory(ListingCategory category) {
        return switch (category) {
            case CAR -> new Car();
            case TRUCK -> new Truck();
        };
    }

    public Vehicle() {
        properties = new HashMap<>();
        mandatoryProperties = new HashMap<>(Map.of(
                BRAND, String.class,
                MODEL, String.class,
                COLOR, String.class,
                YEAR, Integer.class,
                CONDITION, String.class
        ));
        optionalProperties = new HashMap<>(Map.of(
                FUEL_TYPE, String.class
        ));
    }

    public Set<String> getMandatoryProperties() {
        return mandatoryProperties.keySet();
    }

    public String getOptionalProperties() {
        return String.join("/", optionalProperties.keySet());
    }

    public <V> V getProperty(String propertyName) {
        return (V) properties.get(propertyName);
    }

    public void setProperty(String propertyName, Object value) {
        properties.put(propertyName, value);
    }

    public boolean isValidMandatoryProperty(String propertyName) {
        return mandatoryProperties.containsKey(propertyName);
    }

    public boolean isValidOptionalProperty(String propertyName) {
        return optionalProperties.containsKey(propertyName);
    }

    public Class<?> getPropertyType(String propertyName) {
        return mandatoryProperties.getOrDefault(propertyName, optionalProperties.get(propertyName));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) obj;
        return properties.equals(vehicle.properties);
    }

    @Override
    public String toString() {
        String[] className = this.getClass().getName().split("\\.");
        String result = className[className.length - 1] + "{";
        for (String property : properties.keySet()) {
            result += property + "=" + properties.get(property) + ", ";
        }
        if (!properties.isEmpty()) {
            result = result.substring(0, result.length() - 2);
        }
        return result + "}";
    }
}
