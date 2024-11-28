import java.util.Date;
import java.util.List;

public class Trip {
    private String destination;
    private String accommodation;
    private Date startDate;
    private Date endDate;
    private String airline;
    private List<String> activities;

    // Constructor
    public Trip(String destination, String accommodation, Date startDate, Date endDate, String airline, List<String> activities) {
        this.destination = destination;
        this.accommodation = accommodation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.airline = airline;
        this.activities = activities;
    }

    // Getters and Setters
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }
}
