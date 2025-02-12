package mobile.listings;

import mobile.notifications.NotificationService;
import mobile.vehicles.Car;
import mobile.vehicles.Truck;
import mobile.vehicles.Vehicle;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ListingService {
    private final ListingStorage listingStorage;
    private final NotificationService notificationService;

    public ListingService(ListingStorage listingStorage, NotificationService notificationService) {
        this.listingStorage = listingStorage;
        this.notificationService = notificationService;
    }

    public void addListing(Listing listing) {
        listingStorage.addListing(listing);
        notificationService.onNewListingAdded(listing);
    }

    public List<Listing> getListings() {
        return listingStorage.getListings();
    }

    public void createListingFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        String title = "";
        do {
            System.out.println("Enter listing title: ");
            title = scanner.nextLine();
        } while (title.isEmpty());

        Vehicle vehicle;
        outer:
        do {
            System.out.println("Select listing category (CAR/TRUCK): ");
            String category = scanner.nextLine();
            switch (category) {
                case "CAR" -> {
                    vehicle = new Car();
                    break outer;
                }
                case "TRUCK" -> {
                    vehicle = new Truck();
                    break outer;
                }
                default -> System.out.println("Invalid category");
            }
        } while (true);

        int price;
        do {
            System.out.println("Enter listing price: ");
            try {
                price = scanner.nextInt();
                if (price < 0) {
                    System.out.println("Please enter a positive price");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid price");
                scanner.next();
            }
        } while (true);

        System.out.println("Enter listing description: ");
        String description = scanner.next();

        for (String property : vehicle.getMandatoryProperties()) {
            String value = "";
            do {
                System.out.println("Enter " + property + ": ");
                value = scanner.nextLine();
            } while (value.isEmpty());
            vehicle.setProperty(property, value);
        }

        for (String property : vehicle.getOptionalProperties()) {
            System.out.println("Enter " + property + ": ");
            vehicle.setProperty(property, scanner.nextLine());
        }

        scanner.close();

        Listing listing = new Listing(vehicle, title, price, description);
        listingStorage.addListing(listing);
        notificationService.onNewListingAdded(listing);
    }
}
