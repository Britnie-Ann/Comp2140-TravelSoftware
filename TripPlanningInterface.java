import java.util.List;
import java.util.Date;

public class TripPlanningInterface {

    private Date travelDate;
    private String destination;
    private String accommodation;
    private ArrayList<String> activities; 

    // Constructor
    public TripPlanningInterface() {
        this.activities = new ArrayList<>();
    }

    //selects a destination
    public void selectDestination(String destination) {
        this.destination = destination;
        System.out.println("Destination selected is: " + destination);
    }

    //  selects travel dates
    public void selectTravelDates(Date startDate, Date endDate) {
        this.travelDate = startDate;
        System.out.println("Travel dates selected are: " + startDate.toString() + " to " + endDate.toString());
    }

    // selects accommodation
    public void selectAccommodation(String accommodation) {
        this.accommodation = accommodation;
        System.out.println("Accommodation selected: " + accommodation);
    }

    //  adds an activity to the itinerary
    public void addActivity(String activity) {
        this.activities.add(activity);
        System.out.println("Activity added is: " + activity);
    }

    // removes an activity from the itinerary
    public void removeActivity(String activity) {
        if (this.activities.contains(activity)) {
            this.activities.remove(activity);
            System.out.println("Activity removed: " + activity);
        } else {
            System.out.println("Activity not found: " + activity);
        }
    }

    // view the current itinerary
    public void viewItinerary() { 
        System.out.println("Trip Itinerary:");
        System.out.println("Destination: " + this.destination);
        System.out.println("Travel Dates: " + this.travelDate.toString());
        System.out.println("Accommodation: " + this.accommodation);
        System.out.println("Activities: " + String.join(", ", this.activities));
    }

    // edits the itinerary 
    public void editItinerary(String newDestination, String newAccommodation, List<String> newActivities) {
        this.destination = newDestination;
        this.accommodation = newAccommodation;
        this.activities = newActivities;
    }

    // confirm the itinerary
    public void confirmItinerary() {
        System.out.println("Itinerary confirmed successfully!");
        viewItinerary();
    }

    //  save the itinerary
    public void saveItinerary() {
        public void saveItineraryToFile(int userId) {
            ObjectMapper objectMapper = new ObjectMapper();
            Trip trip = new Trip(userId, this.destination, this.travelDate, this.accommodation, this.activities);
    
            try {
                // Save the trip data to a JSON file
                objectMapper.writeValue(new File("itinerary_" + userId + ".json"), trip);
                System.out.println("Itinerary saved to file.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to save itinerary.");
            }
        }
        
        System.out.println("Itinerary saved.");
    }

    // Function to cancel the trip (reset the itinerary)
    public void cancelTrip() {
     this.destination = null;
     this.accommodation = null;
     this.activities.clear();
       System.out.println("Trip canceled.");
    }

    // Getter methods
    public Date getTravelDate() {
        return travelDate;
    }

   
    public String getDestination() {
        return destination;
    }

    public String getAccommodation() {
        return accommodation;
    }

    
    public ArrayList<String> getActivities() {
        return activities;
    }
}

