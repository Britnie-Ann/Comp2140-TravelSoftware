import java.util.ArrayList;
import java.util.List;

public class TripManager {
    private List<Trip> trips;

    public TripManager() {
        this.trips = new ArrayList<>();
    }

    // Add a new trip to the list
    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    // Get all trips
    public List<Trip> getTrips() {
        return trips;
    }

    // Save or update an itinerary
    public void saveItinerary(Trip trip) {
        System.out.println("Saving itinerary: " + trip.getDestination());
    }

    public Trip getItinerary(String destination) {
        for (Trip trip : trips) {
            if (trip.getDestination().equalsIgnoreCase(destination)) {
                return trip;
            }
        }
        return null; // If no trip is found
    }
}

