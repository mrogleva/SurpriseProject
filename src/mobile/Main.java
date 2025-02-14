package mobile;

import mobile.listings.Listing;
import mobile.listings.ListingCategory;
import mobile.listings.ListingService;
import mobile.listings.ListingStorage;
import mobile.notifications.NotificationService;
import mobile.parser.Searcher;
import mobile.search.Filter;
import mobile.search.FilterBuilder;
import mobile.vehicles.Car;
import mobile.vehicles.Vehicle;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static String YES = "yes";
    public static String NO = "no";

    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        ListingStorage listingStorage = new ListingStorage();
        ListingService listingService = new ListingService(listingStorage, notificationService);

        // Fill some initial listings
        initListings(listingStorage);

        Scanner scanner = new Scanner(System.in);
        int input = -1;
        do {
            System.out.println("Pick an option:");
            System.out.println("1. Create new listing");
            System.out.println("2. List all vehicles");
            System.out.println("3. Search for vehicles");
            System.out.println("0. Exit");
            System.out.println("\nEnter your choice:");
            try {
                input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1 -> listingService.createListingFromUserInput(scanner);
                    case 2 -> {
                        for (Listing listing : listingService.getListings()) {
                            System.out.println(listing);
                        }
                    }
                    case 3 -> {
                        List<Filter<Listing>> filters = FilterBuilder.buildSearchFromUserInput(scanner);
                        List<Listing> searchResults = Searcher.search(listingService.getListings(), filters);
                        for (Listing listing : searchResults) {
                            System.out.println(listing);
                        }
                    }
                    case 0 -> {
                        System.out.println("Exiting");
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Invalid option");
            }
        } while (input != 0);
        scanner.close();
    }

    private static void initListings(ListingStorage listingStorage) {
        Car civic = new Car();
        civic.setProperty(Vehicle.BRAND, "Honda");
        civic.setProperty(Vehicle.MODEL, "Civic");
        civic.setProperty(Vehicle.COLOR, "Black");
        civic.setProperty(Vehicle.YEAR, "2022");
        civic.setProperty(Vehicle.CONDITION, "Used");
        civic.setProperty(Vehicle.NB_DOORS, "5");
        civic.setProperty(Car.HEATED_SEATS, "true");
        Listing civicListing = new Listing(ListingCategory.CAR, "Honda Civic", 36000d, "Great car", civic);

        Car hrv = new Car();
        hrv.setProperty(Vehicle.BRAND, "Honda");
        hrv.setProperty(Vehicle.MODEL, "HRV");
        hrv.setProperty(Vehicle.COLOR, "White");
        hrv.setProperty(Vehicle.YEAR, "2016");
        hrv.setProperty(Vehicle.CONDITION, "New");
        hrv.setProperty(Vehicle.NB_DOORS, "5");
        hrv.setProperty(Car.HEATED_SEATS, "true");
        Listing hrvListing = new Listing(ListingCategory.CAR, "Honda HRV", 23000d, "Great car", hrv);

        listingStorage.addListing(civicListing);
        listingStorage.addListing(hrvListing);
    }
}
