package mobile.notifications.channels;

import mobile.notifications.external.PigeonNotifier;

public class PigeonNotificationChannel implements NotificationChannel{
    private final PigeonNotifier pigeonNotifier;
    private final String address;

    public PigeonNotificationChannel(PigeonNotifier pigeonNotifier, String address) {
        this.pigeonNotifier = pigeonNotifier;
        this.address = address;
    }

    @Override
    public void notify(String title, String message) {
        pigeonNotifier.sendPigeon(address, 1, title + System.lineSeparator() + message);
    }
}
