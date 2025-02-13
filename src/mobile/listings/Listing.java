package mobile.listings;

import mobile.vehicles.Vehicle;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class Listing {
    private final ListingCategory category;
    private final String title;
    private final Double price;
    private final String description;
    private final Vehicle vehicle;
    private final LocalDateTime createdDate;

    public Listing(ListingCategory category, String title, Double price, String description, Vehicle vehicle) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
        this.vehicle = vehicle;
        createdDate = LocalDateTime.now();
    }

    public ListingCategory getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Listing listing)) return false;
        return price == listing.price
                && Objects.equals(vehicle, listing.vehicle)
                && Objects.equals(title, listing.title)
                && Objects.equals(description, listing.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle, title, price, description);
    }

    @Override
    public String toString() {
        return "Listing{" +
            "vehicle=" + vehicle +
            ", title='" + title + '\'' +
            ", price=" + price +
            ", description='" + description + '\'' +
            ", createdDate=" + createdDate +
            '}';
    }
}