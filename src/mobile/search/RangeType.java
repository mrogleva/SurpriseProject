package mobile.search;

public enum RangeType {
    CASE_INSENSITIVE, EXACT, RANGE;

    private static final String rangeTypesForNumbers = EXACT.name().toLowerCase() + "/" + RANGE.name().toLowerCase();
    private static final String rangeTypesForStrings = CASE_INSENSITIVE.name().toLowerCase() + "/" + EXACT.name().toLowerCase();
    private static final String rangeTypesForBooleans = EXACT.name().toLowerCase();

    public static RangeType fromString(String category) {
        return RangeType.valueOf(category.toUpperCase());
    }

    public static String getRanges(Class<?> propertyType) {
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

    public static boolean isRangeTypeValidForPropertyType(RangeType rangeType, Class<?> propertyType) {
        if (Integer.class.equals(propertyType) || Double.class.equals(propertyType)) {
            return rangeType == EXACT || rangeType == RANGE;
        }
        if (String.class.equals(propertyType)) {
            return rangeType == CASE_INSENSITIVE || rangeType == EXACT;
        }
        if (Boolean.class.equals(propertyType)) {
            return rangeType == EXACT;
        }
        return false;
    }
}
