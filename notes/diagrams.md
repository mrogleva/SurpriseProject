# Първоначална диаграма

![Diagram](img/{D86E3EE3-0CF3-4B9C-BCD9-D66865E36FEF}.png)

```@plantuml
@startuml
class User {
  - id: int
  - username: String
  - password: String
  - email: String
  - phone: String
  + register()
  + login()
  + logout()
  + contactSeller()
}
 
abstract class Vehicle {
  - id: int
  + getDetails()
  + calculateDepreciation()
}
 
class Car {
  + numDoors: int
}
class Truck {
  + loadCapacity: double
}
class Motorcycle {
  + engineCapacity: double
}
Vehicle <|-- Car
Vehicle <|-- Truck
Vehicle <|-- Motorcycle
 
class Listing {
  - id: int
  - title: String
  - description: String
  - vehicle: Vehicle
  - seller: User
  - price: double
  + editListing()
  + deleteListing()
}
 
class Database {
  - users: List<User>
  - listings: List<Listing>
  - vehicles: List<Vehicle>
  + addUser()
  + addListing()
  + getListings()
  + search(filter)
}
 
class SearchEngine {
  + executeSearch(ProductProperties currentFilter)

}
 
class VehicleFactory {
  + createVehicle()
}
 
class NotificationService {
  + subscribe(User)
  + notifySubscribers(Listing)
}

struct ProductProperties {
 //contains all possible vehicle properties
 //contains meta information about the properties
 //(number,...)
}
 
User --> Listing : creates/manages
Listing --> Vehicle : aggregates
Listing --> User : seller
Database --> User : manages
Database --> Listing : manages
Database --> Vehicle : manages
Database --> SearchEngine : filters Listings
VehicleFactory --> Vehicle : creates
NotificationService --> User : notifies
Vehicle *-> ProductProperties
 
@enduml
```