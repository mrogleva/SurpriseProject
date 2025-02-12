package mobile.notifications.channels;

import mobile.notifications.external.EmailNotifier;

public class EmailNotificationChannel implements NotificationChannel {
    private final EmailNotifier emailNotifier;
    private final String email;

    public EmailNotificationChannel(EmailNotifier emailNotifier, String email) {
        this.emailNotifier = emailNotifier;
        this.email = email;
    }

    @Override
    public void notify(String title, String message) {
        emailNotifier.sendEmail(email, title, message);
    }
}
