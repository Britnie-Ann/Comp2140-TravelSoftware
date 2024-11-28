import java.util.Date;
import java.util.List;

public class Trip {

    private int userId;
    private String destination;
    private Date travelDate;
    private String accommodation;
    private ArrayList<String> activities; 

    // Constructor
    public Trip(int userId, String destination, Date travelDate, String accommodation, List<String> activities) {
        this.userId = userId;
        this.destination = destination;
        this.travelDate = travelDate;
        this.accommodation = accommodation;
        this.activities = activities;
    }

    // Getter and setter methods
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public ArrayList<String> getActivities() { 
        return activities;
    }

    public void setActivities(ArrayList<String> activities) { 
        this.activities = activities;
    }

    // You may also want to override toString() for easier display
    @Override
    public String toString() {
        return "Trip{" +
                "userId=" + userId +
                ", destination='" + destination + '\'' +
                ", travelDate=" + travelDate +
                ", accommodation='" + accommodation + '\'' +
                ", activities=" + activities +
                '}';
    }
}
