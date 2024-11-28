import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TripPlanningInterfaceGUI extends JFrame {
    private JTextField destinationField;
    private JTextField accommodationField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField airlineField;
    private JTextArea activitiesArea;
    private JButton saveButton;
    private JButton viewItineraryButton;
    private TripManager tripManager;  // Manages the trips (save, load, etc.)

    public TripPlanningInterfaceGUI(TripManager tripManager) {
        this.tripManager = tripManager;

        // Set up the window
        setTitle("Trip Planning Interface");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize components
        destinationField = new JTextField(20);
        accommodationField = new JTextField(20);
        startDateField = new JTextField(10);
        endDateField = new JTextField(10);
        airlineField = new JTextField(20);
        activitiesArea = new JTextArea(5, 20);
        saveButton = new JButton("Save Itinerary");
        viewItineraryButton = new JButton("View Itinerary");

        // Add components to the frame
        add(new JLabel("Destination:"));
        add(destinationField);
        add(new JLabel("Accommodation:"));
        add(accommodationField);
        add(new JLabel("Start Date (yyyy-mm-dd):"));
        add(startDateField);
        add(new JLabel("End Date (yyyy-mm-dd):"));
        add(endDateField);
        add(new JLabel("Airline:"));
        add(airlineField);
        add(new JLabel("Activities:"));
        add(new JScrollPane(activitiesArea));
        add(saveButton);
        add(viewItineraryButton);

        // Set button actions
        saveButton.addActionListener(e -> handleSaveItinerary());
        viewItineraryButton.addActionListener(e -> handleViewItinerary());

        // Disable view itinerary button initially (until there's a saved itinerary)
        viewItineraryButton.setEnabled(false);
    }

    // Handle saving the itinerary
    private void handleSaveItinerary() {
        // Check if all fields are filled
        if (isAllFieldsFilled()) {
            // Get the input data from the fields
            String destination = destinationField.getText();
            String accommodation = accommodationField.getText();
            String startDateStr = startDateField.getText();
            String endDateStr = endDateField.getText();
            String airline = airlineField.getText();
            List<String> activities = List.of(activitiesArea.getText().split("\n"));

            try {
                // Parse the start and end date strings into Date objects
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date startDate = dateFormat.parse(startDateStr);
                java.util.Date endDate = dateFormat.parse(endDateStr);

                // Create a new Trip object
                Trip trip = new Trip(destination, accommodation, startDate, endDate, airline, activities);

                // Save the trip through the trip manager
                tripManager.addTrip(trip);
                JOptionPane.showMessageDialog(this, "Itinerary saved successfully!");

                // Enable the 'View Itinerary' button
                viewItineraryButton.setEnabled(true);

                // Clear the fields for the next trip
                clearFields();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields before saving.", "Missing Information", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Check if all fields are filled
    private boolean isAllFieldsFilled() {
        return !destinationField.getText().isEmpty() &&
                !accommodationField.getText().isEmpty() &&
                !startDateField.getText().isEmpty() &&
                !endDateField.getText().isEmpty() &&
                !airlineField.getText().isEmpty() &&
                !activitiesArea.getText().isEmpty();
    }

    // Clear all fields after saving an itinerary
    private void clearFields() {
        destinationField.setText("");
        accommodationField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        airlineField.setText("");
        activitiesArea.setText("");
    }

    // Handle the action when the user clicks "View Itinerary"
    private void handleViewItinerary() {
        // Open the View Itinerary window (will show all saved itineraries and allow for editing)
        ViewItineraryWindow viewItineraryWindow = new ViewItineraryWindow(tripManager);
        viewItineraryWindow.setVisible(true);
        this.setEnabled(false);  // Disable the current window while viewing itineraries
    }
}
