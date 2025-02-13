package mobile.notifications;

import mobile.listings.Listing;
import mobile.search.Filter;
import mobile.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private final List<NotificationRule> notificationRules = new ArrayList<>();

    public void subscribe(NotificationRule notificationRule) {
        notificationRules.add(notificationRule);
    }

    public void onNewListingAdded(Listing listing) {
        for (NotificationRule notificationRule : notificationRules) {
            boolean shouldNotify = true;
            for (Filter<Listing> filter : notificationRule.filters()) {
                if (!filter.matches(listing)) {
                    shouldNotify = false;
                    break;
                }
            }
            if (shouldNotify) {
                String message = listing.getVehicle().getProperty(Vehicle.BRAND) + " "
                    + listing.getVehicle().getProperty(Vehicle.MODEL) +
                    " for " + listing.getPrice();
                notificationRule.channel().notify(
                    "New car found for you!",
                    message
                );
            }
        }
    }
}
