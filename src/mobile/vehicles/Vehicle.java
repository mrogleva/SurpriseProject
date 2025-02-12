package mobile.vehicles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Vehicle {

    private final Map<String, Object> properties;
    protected final List<String> mandatoryProperties;
    protected final List<String> optionalProperties;

    public Vehicle() {
        properties = new HashMap<>();
        mandatoryProperties = new ArrayList<>(List.of("brand", "model", "isUsed", "yearManufactured", "color"));
        optionalProperties = new ArrayList<>();
    }

    public List<String> getMandatoryProperties() {
        return mandatoryProperties;
    }

    public List<String> getOptionalProperties() {
        return optionalProperties;
    }

    public Object getProperty(String propertyName) {
        return properties.get(propertyName);
    }

    public void setProperty(String propertyName, Object value) {
        properties.put(propertyName, value);
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
        return properties.equals(vehicle.properties) && mandatoryProperties.equals(vehicle.mandatoryProperties);
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
