package mobile;

import mobile.listings.Listing;
import mobile.listings.ListingService;
import mobile.listings.ListingStorage;
import mobile.notifications.NotificationService;
import mobile.vehicles.Car;
import mobile.vehicles.Truck;
import mobile.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//public class Main {
//    public static void main(String[] args) {
//        Car car = new Car();
//        Car car2 = new Car("Bmw", "e60", 2000, false);
//
//        // всичките хиляди коли които имаме
//        List<Car> cars = List.of(car, car2);
//
//        List<Filter<Car>> filters = List.of(
//            new ExactValueFilter<>(c -> c.brand(), "Toyota"),
//            new CaseInsensitiveFilter<>(c -> c.model(), "Corolla"),
//            new RangeFilter<>(c -> c.year(), 2000, 2022),
//            new RangeFilter<>(c -> c.brand(), "Bmw", "Toyota")
//        );
//
//        // само колите от филтрите
//        List<Car> matchingCars = filterCars(cars, filters);
//        System.out.println("Matching cars:");
//        matchingCars.forEach(System.out::println);
//    }
//
//    // не му е мястото тук, само за демонстративни цели е
//    private static List<Car> filterCars(List<Car> cars, List<Filter<Car>> filters) {
//        return cars.stream()
//            .filter(car -> filters.stream().allMatch(filter -> filter.matches(car)))
//            .toList();
//    }
//}

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        ListingStorage listingStorage = new ListingStorage();
        ListingService listingService = new ListingService(listingStorage, notificationService);

        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.println("Pick an option: ");
            System.out.println("1. Create new listing");
            System.out.println("2. List all vehicles");
            System.out.println("0. Exit");
            System.out.println("\nEnter your choice: ");
            input = scanner.nextInt();
            switch (input) {
                case 1 -> listingService.createListingFromUserInput();
                case 2 -> {
                    for (Listing listing : listingService.getListings()) {
                        System.out.println(listing);
                    }
                }
                case 0 -> {
                    System.out.println("Exiting");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        } while (input != 0);
        scanner.close();
    }
}
