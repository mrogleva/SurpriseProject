package mobile.search;

import mobile.listings.Listing;
import mobile.listings.ListingCategory;
import mobile.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static mobile.Main.NO;

public class FilterBuilder {
    public static List<Filter<Listing>> buildSearchFromUserInput(Scanner scanner) {
        List<Filter<Listing>> filters = new ArrayList<>();

        System.out.println("Select listing category (" + ListingCategory.getListingCategories() + "):");
        ListingCategory category;
        do {
            try {
                category = ListingCategory.fromString(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category");
            }
        } while (true);
        filters.add(new ExactValueFilter<>(Listing::getCategory, category));

        System.out.println("Do you wish to filter results by price?");
        String property = "price";
        RangeType rangeType = getRangeTypeFromUserInput(scanner, property, Double.class);
        Filter<Listing> filter = getFilterFromRange(scanner, property, Double.class, rangeType, Listing::getPrice);
        if (filter != null) {
            filters.add(filter);
        }

        Vehicle dummyVehicle = Vehicle.getVehicleFromListingCategory(category);
        do {
            System.out.println("Do you wish to filter by anything else?");
            System.out.println("Commonly used filters are " + Vehicle.MANDATORY_PROPERTIES);
            System.out.println("Other possible filters are " + dummyVehicle.getOptionalProperties());
            System.out.println("Enter 'no' to finish filtering");
            System.out.println("\nEnter your choice:");
            property = scanner.nextLine();
            if (NO.equals(property)) {
                break;
            }
            if (dummyVehicle.isValidMandatoryProperty(property) || dummyVehicle.isValidOptionalProperty(property)) {
                Class<?> propertyType = dummyVehicle.getPropertyType(property);
                rangeType = getRangeTypeFromUserInput(scanner, property, propertyType);
                String finalProperty = property;
                filter = getFilterFromRange(scanner, property, propertyType, rangeType, listing -> listing.getVehicle().getProperty(finalProperty));
                if (filter != null) {
                    filters.add(filter);
                }
            } else {
                System.out.println("Invalid property");
            }
        } while (true);

        return filters;
    }

    private static RangeType getRangeTypeFromUserInput(Scanner scanner, String property, Class<?> propertyType) {
        do {
            String availableRangeTypes = RangeType.getRanges(propertyType);
            if (availableRangeTypes.isEmpty()) {
                break;
            }
            System.out.println("If you wish to filter by " + property + " , please select one of " + availableRangeTypes + ". Otherwise, enter 'no'.");
            String answer = scanner.nextLine();
            if (NO.equals(answer)) {
                break;
            }
            try {
                RangeType rangeType = RangeType.fromString(answer);
                if (RangeType.isRangeTypeValidForPropertyType(rangeType, propertyType)) {
                    return rangeType;
                }
            } catch (IllegalArgumentException e) {
                // ignore
            }
            System.out.println("Invalid range type");
        } while (true);
        return null;
    }

    private static <V extends Comparable<V>> Filter<Listing> getFilterFromRange(Scanner scanner,
                                                                                String property,
                                                                                Class<?> propertyType,
                                                                                RangeType rangeType,
                                                                                FieldExtractor<Listing, V> fieldExtractor) {
        if (rangeType == null) {
            return null;
        }
        do {
            try {
                switch (rangeType) {
                    case CASE_INSENSITIVE -> {
                        System.out.println("Enter " + property + ":");
                        String value = scanner.nextLine();
                        return new CaseInsensitiveFilter<>((FieldExtractor<Listing, String>) fieldExtractor, value);
                    }
                    case EXACT -> {
                        System.out.println("Enter " + property + ":");
                        V value = castUserInput(scanner.nextLine(), propertyType);
                        return new ExactValueFilter<>(fieldExtractor, value);
                    }
                    case RANGE -> {
                        System.out.println("Enter " + property + " range start:");
                        V start = castUserInput(scanner.nextLine(), propertyType);
                        System.out.println("Enter " + property + " range end:");
                        V end = castUserInput(scanner.nextLine(), propertyType);
                        return new RangeFilter<>(fieldExtractor, start, end);
                    }
                    default -> {
                        return null;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid value for " + property);
            }
        } while (true);
    }

    private static <V> V castUserInput(String input, Class<?> propertyType) {
        if (propertyType == String.class) {
            return (V) input;
        } else if (propertyType == Integer.class) {
            return (V) Integer.valueOf(input);
        } else if (propertyType == Double.class) {
            return (V) Double.valueOf(input);
        } else if (propertyType == Boolean.class) {
            return (V) Boolean.valueOf(input);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
