public class TripPlanningApp {
    public static void main(String[] args) {
        // Initialize the TripManager to manage all trip data
        TripManager tripManager = new TripManager();

        // Initialize the TripPlanningInterfaceGUI and pass the tripManager to it
        TripPlanningInterfaceGUI tripPlanningGUI = new TripPlanningInterfaceGUI(tripManager);

        // Initialize the ViewItineraryWindow to allow users to view and edit saved trips
        ViewItineraryWindow viewItineraryWindow = new ViewItineraryWindow(tripManager);

        // Display the Trip Planning Interface GUI
        tripPlanningGUI.setVisible(true);

        // Display the View Itinerary Window (this can be launched after the main GUI is ready)
        viewItineraryWindow.setVisible(true);
    }
}


