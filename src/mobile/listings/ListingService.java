package mobile.listings;

import mobile.notifications.NotificationService;
import mobile.validation.UserInputValidator;
import mobile.vehicles.Vehicle;

import java.util.List;
import java.util.Scanner;

import static mobile.Main.NO;
import static mobile.Main.YES;

public class ListingService {
    private final ListingStorage listingStorage;
    private final NotificationService notificationService;

    public ListingService(ListingStorage listingStorage, NotificationService notificationService) {
        this.listingStorage = listingStorage;
        this.notificationService = notificationService;
    }

    public List<Listing> getListings() {
        return listingStorage.getListings();
    }

    public void createListingFromUserInput(Scanner scanner) {
        String title;
        do {
            System.out.println("Enter listing title:");
            title = scanner.nextLine();
            if (title.isEmpty()) {
                System.out.println("Please enter a title");
            }
        } while (title.isEmpty());

        ListingCategory category;
        Vehicle vehicle;
        do {
            System.out.println("Select listing category (" + ListingCategory.getListingCategories() + "):");
            try {
                category = ListingCategory.fromString(scanner.nextLine());
                vehicle = Vehicle.getVehicleFromListingCategory(category);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category");
            }
        } while (true);

        double price;
        do {
            System.out.println("Enter listing price:");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price <= 0.0d) {
                    System.out.println("Please enter a positive price");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid price");
            }
        } while (true);

        System.out.println("Enter listing description: ");
        String description = scanner.nextLine();

        for (String property : vehicle.getMandatoryProperties()) {
            setVehiclePropertyFromUserInput(scanner, property, vehicle);
        }

        System.out.println("You have entered all of the mandatory properties.");

        if (!vehicle.getOptionalProperties().isEmpty()) {
            String answer;
            do {
                System.out.println("Do you wish to enter any optional property? (yes/no)");
                answer = scanner.nextLine();
                if (YES.equals(answer)) {
                    System.out.println("Select a property you wish to add (" + vehicle.getOptionalProperties() + "). Enter 'no' if you don't wish to select a property.");
                    String property = scanner.nextLine();
                    if (NO.equals(property)) {
                        answer = NO;
                    } else if (vehicle.isValidOptionalProperty(property)) {
                        setVehiclePropertyFromUserInput(scanner, property, vehicle);
                    } else {
                        System.out.println("Invalid property");
                    }
                } else if (!NO.equals(answer)) {
                    System.out.println("Invalid answer");
                }
            } while (!NO.equals(answer));
        }

        Listing listing = new Listing(category, title, price, description, vehicle);
        listingStorage.addListing(listing);

        System.out.println("Listing created successfully!");

        notificationService.onNewListingAdded(listing);
    }

    private static void setVehiclePropertyFromUserInput(Scanner scanner, String property, Vehicle vehicle) {
        do {
            Class<?> propertyType = vehicle.getPropertyType(property);
            if (Boolean.class == propertyType) {
                System.out.println("Enter " + property + " (true/false):");
            } else {
                System.out.println("Enter " + property + ":");
            }
            String value = scanner.nextLine();
            if (value.isEmpty()) {
                System.out.println("Please enter a value");
            } else {
                try {
                    vehicle.setProperty(property, UserInputValidator.castUserInput(value, propertyType));
                    return;
                } catch (Exception e) {
                    System.out.println("Invalid value");
                }
            }
        } while (true);
    }
}
