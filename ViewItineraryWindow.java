import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ViewItineraryWindow extends JFrame {
    private TripManager tripManager;
    private JList<String> itineraryList;
    private DefaultListModel<String> listModel;
    private JTextField destinationField;
    private JTextField accommodationField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField airlineField;
    private JTextArea activitiesArea;
    private JButton editButton;
    private JButton saveButton;
    private JButton cancelButton;
    private Trip selectedTrip;

    public ViewItineraryWindow(TripManager tripManager) {
        this.tripManager = tripManager;
        setTitle("View and Edit Itinerary");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // List model to display itineraries
        listModel = new DefaultListModel<>();
        itineraryList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(itineraryList);
        listScrollPane.setPreferredSize(new Dimension(350, 150));

        // Input fields for itinerary details
        destinationField = new JTextField(20);
        accommodationField = new JTextField(20);
        startDateField = new JTextField(10);
        endDateField = new JTextField(10);
        airlineField = new JTextField(20);
        activitiesArea = new JTextArea(5, 20);
        editButton = new JButton("Edit");
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");

        // Add components to the frame
        add(new JLabel("Select Itinerary:"));
        add(listScrollPane);
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
        add(editButton);
        add(saveButton);
        add(cancelButton);

        // Load itineraries into the list
        loadItineraries();

        // Set up event listeners
        itineraryList.addListSelectionListener(e -> handleItinerarySelection());
        editButton.addActionListener(e -> handleEdit());
        saveButton.addActionListener(e -> handleSave());
        cancelButton.addActionListener(e -> handleCancel());
    }

    // Load itineraries into the list
    private void loadItineraries() {
        listModel.clear();
        List<Trip> trips = tripManager.getTrips(); // Assuming tripManager has a getTrips() method
        for (Trip trip : trips) {
            listModel.addElement(trip.getDestination() + " - " + trip.getStartDate());
        }
    }

    // Handle itinerary selection from the list
    private void handleItinerarySelection() {
        int index = itineraryList.getSelectedIndex();
        if (index != -1) {
            selectedTrip = tripManager.getTrips().get(index);
            showItineraryDetails(selectedTrip);
        }
    }

    // Display selected itinerary details in the text fields
    private void showItineraryDetails(Trip trip) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        destinationField.setText(trip.getDestination());
        accommodationField.setText(trip.getAccommodation());
        startDateField.setText(dateFormat.format(trip.getStartDate()));
        endDateField.setText(dateFormat.format(trip.getEndDate()));
        airlineField.setText(trip.getAirline());
        activitiesArea.setText(String.join("\n", trip.getActivities()));
    }

    // Handle the Edit button click (make the fields editable)
    private void handleEdit() {
        destinationField.setEditable(true);
        accommodationField.setEditable(true);
        startDateField.setEditable(true);
        endDateField.setEditable(true);
        airlineField.setEditable(true);
        activitiesArea.setEditable(true);
        editButton.setEnabled(false);
        saveButton.setEnabled(true);
        cancelButton.setEnabled(true);
    }

    // Handle the Save button click (save the updated itinerary)
    private void handleSave() {
        if (selectedTrip != null) {
            // Update the itinerary with the new values
            selectedTrip.setDestination(destinationField.getText());
            selectedTrip.setAccommodation(accommodationField.getText());
            selectedTrip.setAirline(airlineField.getText());
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                selectedTrip.setStartDate(dateFormat.parse(startDateField.getText()));
                selectedTrip.setEndDate(dateFormat.parse(endDateField.getText()));
                selectedTrip.setActivities(List.of(activitiesArea.getText().split("\n")));

                // Save the updated itinerary
                tripManager.saveItinerary(selectedTrip);

                // Refresh the itinerary list view
                loadItineraries();

                // Disable editing and clear the fields
                cancelEditing();
                JOptionPane.showMessageDialog(this, "Itinerary updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    // Handle the Cancel button click (cancel the edit)
    private void handleCancel() {
        cancelEditing();
    }

    // Cancel editing mode and reset the UI
    private void cancelEditing() {
        destinationField.setEditable(false);
        accommodationField.setEditable(false);
        startDateField.setEditable(false);
        endDateField.setEditable(false);
        airlineField.setEditable(false);
        activitiesArea.setEditable(false);
        editButton.setEnabled(true);
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        loadItineraries(); // Refresh the itinerary list
    }
}
