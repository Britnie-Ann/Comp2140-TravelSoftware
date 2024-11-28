import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification {

    public static void main(String[] args) {
        // Set up the main window (frame)
        JFrame frame = new JFrame("Trip Notifications");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components for user input
        JTextField destinationField = new JTextField(20);
        JTextField dateField = new JTextField(10);
        JTextField emailField = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        // Layout of components
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Destination:"));
        panel.add(destinationField);
        panel.add(new JLabel("Date (yyyy-mm-dd):"));
        panel.add(dateField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(submitButton);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String date = dateField.getText();
                String email = emailField.getText();

                // Step 1: Send confirmation notification
                sendConfirmationNotification(destination, date, email);

                // Step 2: Send a package recommendation (for demonstration)
                sendPackageRecommendations(destination, email);

                // Step 3: Schedule reminder for the trip
                scheduleReminder(destination, date, email);
            }
        });
    }

    // Method to send confirmation notification
    private static void sendConfirmationNotification(String destination, String date, String email) {
        String message = "Trip Confirmation: \nDestination: " + destination +
                         "\nDate: " + date + "\nEmail: " + email +
                         "\nYour trip has been confirmed!";
        JOptionPane.showMessageDialog(null, message, "Trip Confirmation", JOptionPane.INFORMATION_MESSAGE);
        
        // Simulate sending an email
        System.out.println("Sending confirmation notification: ");
        System.out.println(message);
    }

    // Method to send package recommendations based on the trip destination
    private static void sendPackageRecommendations(String destination, String email) {
        // Simulated package recommendations
        String recommendations = "Based on your destination, we recommend the following packages:\n";

        if (destination.toLowerCase().contains("beach")) {
            recommendations += "- Beachfront Resort Package\n- Diving Adventure Package";
        } else if (destination.toLowerCase().contains("mountain")) {
            recommendations += "- Mountain Hiking Expedition\n- Cozy Lodge Package";
        } else {
            recommendations += "- Explore our Global Travel Deals!";
        }

        JOptionPane.showMessageDialog(null, recommendations, "Package Recommendations", JOptionPane.INFORMATION_MESSAGE);

        // Simulate sending the recommendations
        System.out.println("Sending package recommendations to: " + email);
        System.out.println(recommendations);
    }

    // Method to schedule reminder notifications 1 day before the trip
    private static void scheduleReminder(String destination, String date, String email) {
        // Parse the date entered by the user
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date tripDate = sdf.parse(date);
            long oneDayBeforeTrip = tripDate.getTime() - (24 * 60 * 60 * 1000); // 1 day before the trip

            // Schedule a reminder notification 1 day before the trip
            Timer reminderTimer = new Timer();
            reminderTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    sendReminderNotification(destination, date, email);
                }
            }, new Date(oneDayBeforeTrip));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to send a reminder notification 1 day before the trip
    private static void sendReminderNotification(String destination, String date, String email) {
        String message = "Reminder: \nYour trip to " + destination +
                         " is coming up tomorrow! \nDate: " + date +
                         "\nPlease make sure you're prepared!";
        JOptionPane.showMessageDialog(null, message, "Trip Reminder", JOptionPane.WARNING_MESSAGE);

        // Simulate sending a reminder notification
        System.out.println("Sending reminder notification: ");
        System.out.println(message);
    }
}
