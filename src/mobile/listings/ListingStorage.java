package mobile.listings;

import java.util.ArrayList;
import java.util.List;

public class ListingStorage {
    private final List<Listing> listings = new ArrayList<>();

    public void addListing(Listing listing) {
        listings.add(listing);
    }

    public List<Listing> getListings() {
        return listings;
    }
}
