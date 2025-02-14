package mobile.vehicles;

import mobile.listings.ListingCategory;
import mobile.validation.UserInputValidator;

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

    private final Map<String, Object> propertiesValues;

    protected final Map<String, Class<?>> mandatoryPropertiesTypes;
    protected final Map<String, Class<?>> optionalPropertiesTypes;

    public static Vehicle getVehicleFromListingCategory(ListingCategory category) {
        return switch (category) {
            case CAR -> new Car();
            case TRUCK -> new Truck();
        };
    }

    public Vehicle() {
        propertiesValues = new HashMap<>();
        mandatoryPropertiesTypes = new HashMap<>(Map.of(
                BRAND, String.class,
                MODEL, String.class,
                COLOR, String.class,
                YEAR, Integer.class,
                CONDITION, String.class
        ));
        optionalPropertiesTypes = new HashMap<>(Map.of(
                FUEL_TYPE, String.class
        ));
    }

    public Set<String> getMandatoryPropertiesTypes() {
        return mandatoryPropertiesTypes.keySet();
    }

    public String getOptionalPropertiesTypes() {
        return String.join("/", optionalPropertiesTypes.keySet());
    }

    public <V> V getProperty(String propertyName) {
        return (V) propertiesValues.get(propertyName);
    }

    public void setProperty(String propertyName, String value) throws IllegalArgumentException{
        Object correctTypeValue = castPropertyValue(propertyName, value);
        propertiesValues.put(propertyName, correctTypeValue);
    }

    public boolean isValidMandatoryProperty(String propertyName) {
        return mandatoryPropertiesTypes.containsKey(propertyName);
    }

    public boolean isValidOptionalProperty(String propertyName) {
        return optionalPropertiesTypes.containsKey(propertyName);
    }

    public Class<?> getPropertyType(String propertyName) {
        return mandatoryPropertiesTypes.getOrDefault(propertyName, optionalPropertiesTypes.get(propertyName));
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
        return propertiesValues.equals(vehicle.propertiesValues);
    }

    @Override
    public String toString() {
        String[] className = this.getClass().getName().split("\\.");
        String result = className[className.length - 1] + "{";
        for (String property : propertiesValues.keySet()) {
            result += property + "=" + propertiesValues.get(property) + ", ";
        }
        if (!propertiesValues.isEmpty()) {
            result = result.substring(0, result.length() - 2);
        }
        return result + "}";
    }

    public <V> V castPropertyValue(String propertyName, String value) {
        return UserInputValidator.castUserInput(value, getPropertyType(propertyName));
    }
}
