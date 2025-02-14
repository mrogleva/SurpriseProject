package mobile.search;

import mobile.listings.Listing;
import mobile.search.filters.Filter;

import java.util.ArrayList;
import java.util.List;

public class Searcher {
    public static List<Listing> search(List<Listing> listings, List<Filter<Listing>> filters) {
        List<Listing> result = new ArrayList<>();
        for (Listing listing : listings) {
            if (isListingMatchingFilters(listing, filters)) {
                result.add(listing);
            }
        }
        return result;
    }

    private static boolean isListingMatchingFilters(Listing listing, List<Filter<Listing>> filters) {
        for (Filter<Listing> filter : filters) {
            if (!filter.matches(listing)) {
                return false;
            }
        }
        return true;
    }
}
