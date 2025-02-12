package mobile.notifications.external;

public class PigeonNotifier {
    public void sendPigeon(String address, Integer pigeonNumber, String message) {
        System.out.println("Sending pigeon to " + address + " with message: " + message);
    }
}
