package mobile.notifications;

import mobile.listings.Listing;
import mobile.search.Filter;

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
//                String message = listing.car().brand() + " "
//                    + listing.car().model() +
//                    " for " + listing.price();
//                notificationRule.channel().notify(
//                    "New car found for you!",
//                    message
//                );
            }
        }
    }
}
