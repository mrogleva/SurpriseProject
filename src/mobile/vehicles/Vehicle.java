package mobile.vehicles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Vehicle {
    public static final String BRAND = "brand";
    public static final String MODEL = "model";
    public static final String COLOR = "color";
    public static final String YEAR = "year";
    public static final String CONDITION = "condition";

    public static final String NB_DOORS = "nbOfDoors";

    private final Map<String, Object> properties;
    protected final List<String> mandatoryProperties;
    protected final List<String> optionalProperties;

    public Vehicle() {
        properties = new HashMap<>();
        mandatoryProperties = new ArrayList<>(List.of(BRAND, MODEL, COLOR, YEAR, CONDITION));
        optionalProperties = new ArrayList<>();
    }

    public List<String> getMandatoryProperties() {
        return mandatoryProperties;
    }

    public String getOptionalProperties() {
        return String.join("/", optionalProperties);
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public void setProperty(String propertyName, Object value) {
        properties.put(propertyName, value);
    }

    public boolean isValidOptionalProperty(String propertyName) {
        return optionalProperties.contains(propertyName);
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
