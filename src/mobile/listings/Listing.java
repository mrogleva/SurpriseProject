package mobile.listings;

import mobile.vehicles.Vehicle;

import java.time.LocalDateTime;
import java.util.Objects;

public class Listing {
    private final Vehicle vehicle;
    private final String title;
    private final int price;
    private final String description;
    private final LocalDateTime createdDate;

    public Listing(Vehicle vehicle, String title, int price, String description) {
        this.vehicle = vehicle;
        this.title = title;
        this.price = price;
        this.description = description;
        createdDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Listing listing)) return false;
        return price == listing.price && Objects.equals(vehicle, listing.vehicle) && Objects.equals(title, listing.title) && Objects.equals(description, listing.description);
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