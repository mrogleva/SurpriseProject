package mobile.listings;

import mobile.cmd.UserInputHandler;
import mobile.notifications.NotificationService;
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

    public void createListingFromUserInput(UserInputHandler handler) {
        String title = handler.getInputRequireNonEmpty("title");

        String category = handler.getInputFromOptions("category", ListingCategory.getListingCategoriesNames());
        ListingCategory listingCategory = ListingCategory.fromString(category);
        Vehicle vehicle = Vehicle.getVehicleFromListingCategory(listingCategory);

        double price;
        do {
            try {
                price = Double.parseDouble(handler.getInputRequireNonEmpty("price"));
                if (price <= 0.0d) {
                    System.out.println("Please enter a positive price");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price");
            }
        } while (true);

        String description = handler.getInputRequireNonEmpty("description");

        for (String property : vehicle.getMandatoryPropertiesTypes()) {
            setVehiclePropertyFromUserInput(handler, property, vehicle);
        }

        System.out.println("You have entered all of the mandatory properties.");

        if (!vehicle.getOptionalPropertiesTypes().isEmpty()) {
            do {
                String property = handler.getInputWithFromOptionsSkipable(
                        "Select an optional property you wish to add.",
                        vehicle.getOptionalPropertiesTypesList().toArray(String[]::new));
                if (property == null) {
                    break;
                }
                setVehiclePropertyFromUserInput(handler, property, vehicle);
            } while (true);
        }

        Listing listing = new Listing(listingCategory, title, price, description, vehicle);
        listingStorage.addListing(listing);
        System.out.println("\nListing created successfully!\n");
        notificationService.onNewListingAdded(listing);
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

        for (String property : vehicle.getMandatoryPropertiesTypes()) {
            setVehiclePropertyFromUserInput(scanner, property, vehicle);
        }

        System.out.println("You have entered all of the mandatory properties.");

        if (!vehicle.getOptionalPropertiesTypes().isEmpty()) {
            String answer;
            do {
                System.out.println("Do you wish to enter any optional property? (yes/no)");
                answer = scanner.nextLine();
                if (YES.equals(answer)) {
                    System.out.println("Select a property you wish to add (" + vehicle.getOptionalPropertiesTypes() + "). Enter 'no' if you don't wish to select a property.");
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
        System.out.println();
        System.out.println("Listing created successfully!");
        System.out.println();
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
                    vehicle.setProperty(property, value);
                    return;
                } catch (Exception e) {
                    System.out.println("Invalid value");
                }
            }
        } while (true);
    }

    private static void setVehiclePropertyFromUserInput(UserInputHandler handler, String property, Vehicle vehicle) {
        do {
            Class<?> propertyType = vehicle.getPropertyType(property);
            String value;
            if (Boolean.class == propertyType) {
                value = handler.getInputFromOptions(property, "true", "false");
            } else {
                value = handler.getInputRequireNonEmpty(property);
            }
            try {
                vehicle.setProperty(property, value);
                return;
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        } while (true);
    }
}
