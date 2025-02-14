package mobile.search.filters;

public enum FilterType {
    CASE_INSENSITIVE, EXACT, RANGE;

    private static final String rangeTypesForNumbers = EXACT.name().toLowerCase() + "/" + RANGE.name().toLowerCase();
    private static final String rangeTypesForStrings = CASE_INSENSITIVE.name().toLowerCase() + "/" + EXACT.name().toLowerCase();
    private static final String rangeTypesForBooleans = EXACT.name().toLowerCase();

    public static FilterType fromString(String category) {
        return FilterType.valueOf(category.toUpperCase());
    }

    public static String getFilterTypes(Class<?> propertyType) {
        if (Integer.class.equals(propertyType) || Double.class.equals(propertyType)) {
            return rangeTypesForNumbers;
        }
        if (String.class.equals(propertyType)) {
            return rangeTypesForStrings;
        }
        if (Boolean.class.equals(propertyType)) {
            return rangeTypesForBooleans;
        }
        return "";
    }

    public static boolean isRangeTypeValidForPropertyType(FilterType filterType, Class<?> propertyType) {
        if (Integer.class.equals(propertyType) || Double.class.equals(propertyType)) {
            return filterType == EXACT || filterType == RANGE;
        }
        if (String.class.equals(propertyType)) {
            return filterType == CASE_INSENSITIVE || filterType == EXACT;
        }
        if (Boolean.class.equals(propertyType)) {
            return filterType == EXACT;
        }
        return false;
    }
}
