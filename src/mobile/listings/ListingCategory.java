package mobile.listings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ListingCategory {
    CAR, TRUCK;

    public static String getListingCategories() {
        return Stream.of(ListingCategory.values()).map(c -> c.name().toLowerCase()).collect(Collectors.joining("/"));
    }

    public static ListingCategory fromString(String category) {
        return ListingCategory.valueOf(category.toUpperCase());
    }

    // TODO: rename to old one
    public static String[] getListingCategoriesNames() {
        return Stream.of(ListingCategory.values()).map(Enum::name).toArray(String[]::new);
    }
}
