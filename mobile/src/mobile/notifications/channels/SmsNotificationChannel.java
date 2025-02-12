package mobile.notifications.channels;

import mobile.notifications.external.SmsNotifier;

public class SmsNotificationChannel implements NotificationChannel {
    private final SmsNotifier smsNotifier;
    private final String phoneNumber;

    public SmsNotificationChannel(SmsNotifier smsNotifier, String phoneNumber) {
        this.smsNotifier = smsNotifier;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void notify(String title, String message) {
        smsNotifier.sendSms(phoneNumber, title + System.lineSeparator() + message);
    }
}
