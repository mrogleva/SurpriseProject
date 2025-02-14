package mobile.search;

import mobile.listings.Listing;
import mobile.listings.ListingCategory;
import mobile.search.filters.*;
import mobile.validation.UserInputValidator;
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
        // Create dummy vehicle from the correct type
        Vehicle dummyVehicle = Vehicle.getVehicleFromListingCategory(category);

        System.out.println("Do you wish to filter results by price?");
        String property = "price";
        String availableFilterTypes = FilterType.getFilterTypes(Double.class);
        FilterType filterType = null;
        if(availableFilterTypes.contains("/")) {
            filterType = getFilterTypeFromUserInput(scanner, property, Double.class);
        }
        else if (!availableFilterTypes.isEmpty()) {
            filterType = FilterType.fromString(availableFilterTypes);
        }
        Filter<Listing> filter = getFilterFromRange(scanner, property, Double.class, filterType, Listing::getPrice);
        if (filter != null) {
            filters.add(filter);
        }

        do {
            System.out.println("Do you wish to filter by anything else?");
            System.out.println("Commonly used filters are " + Vehicle.MANDATORY_PROPERTIES);
            System.out.println("Other possible filters are " + dummyVehicle.getOptionalPropertiesTypes());
            System.out.println("Enter 'no' to finish filtering");
            System.out.println("\nEnter your choice:");
            property = scanner.nextLine();
            if (NO.equals(property)) {
                break;
            }
            if (dummyVehicle.isValidMandatoryProperty(property) || dummyVehicle.isValidOptionalProperty(property)) {
                Class<?> propertyType = dummyVehicle.getPropertyType(property);
                filterType = null;
                availableFilterTypes = FilterType.getFilterTypes(propertyType);
                if(availableFilterTypes.contains("/")) {
                    filterType = getFilterTypeFromUserInput(scanner, property, propertyType);
                }
                else if (!availableFilterTypes.isEmpty()) {
                    filterType = FilterType.fromString(availableFilterTypes);
                }
                String finalProperty = property;
                filter = getFilterFromRange(scanner, property, propertyType, filterType, listing -> listing.getVehicle().getProperty(finalProperty));
                if (filter != null) {
                    filters.add(filter);
                }
            } else {
                System.out.println("Invalid property");
            }
        } while (true);

        return filters;
    }

    private static FilterType getFilterTypeFromUserInput(Scanner scanner, String property, Class<?> propertyType) {
        do {
            String availableFilterTypes = FilterType.getFilterTypes(propertyType);
            if (availableFilterTypes.isEmpty()) {
                break;
            }
            System.out.println("If you wish to filter by " + property + " , please select one of " + availableFilterTypes + ". Otherwise, enter 'no'.");
            String answer = scanner.nextLine();
            if (NO.equals(answer)) {
                break;
            }
            try {
                FilterType filterType = FilterType.fromString(answer);
                if (FilterType.isRangeTypeValidForPropertyType(filterType, propertyType)) {
                    return filterType;
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
                                                                                FilterType filterType,
                                                                                FieldExtractor<Listing, V> fieldExtractor) {
        if (filterType == null) {
            return null;
        }
        do {
            try {
                switch (filterType) {
                    case CASE_INSENSITIVE -> {
                        System.out.println("Enter " + property + ":");
                        String value = scanner.nextLine();
                        return new CaseInsensitiveFilter<>((FieldExtractor<Listing, String>) fieldExtractor, value);
                    }
                    case EXACT -> {
                        System.out.println("Enter " + property + ":");
                        V value = UserInputValidator.castUserInput(scanner.nextLine(), propertyType);
                        return new ExactValueFilter<>(fieldExtractor, value);
                    }
                    case RANGE -> {
                        System.out.println("Enter " + property + " range start:");
                        V start = UserInputValidator.castUserInput(scanner.nextLine(), propertyType);
                        System.out.println("Enter " + property + " range end:");
                        V end = UserInputValidator.castUserInput(scanner.nextLine(), propertyType);
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
}
