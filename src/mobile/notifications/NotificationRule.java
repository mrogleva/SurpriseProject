package mobile.notifications;

import mobile.listings.Listing;
import mobile.notifications.channels.NotificationChannel;
import mobile.search.filters.Filter;

import java.util.List;

public record NotificationRule(
    List<Filter<Listing>> filters,
    NotificationChannel channel
) {}
