import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewManagerInterfaceGUI
{
    private static JFrame mainFrame;

    public static void main(String[] args)
    {
        ceateReviewMenu();
    }

    private static void ceateReviewMenu()
    {
        mainFrame = new JFrame("Review Menu");

        mainFrame.setSize(300, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add Review Button
        var addReviewButton = new JButton("Add Review");
        addReviewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the "Add Review" menu
                createAddReviewMenu();
                hideMainWindow();
            }
        });
        panel.add(addReviewButton);

        // View Reviews Button
        var viewReviewsButton = new JButton("View Reviews");
        viewReviewsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewReviewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the "View Reviews" menu
                createViewReviewsMenu();
                hideMainWindow();
            }
        });
        panel.add(viewReviewsButton);

        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    private static void createAddReviewMenu()
    {
        // Create a new JFrame for the "Add Review" menu
        JFrame addReviewFrame = new JFrame("Add Review Menu");
        addReviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add a WindowListener to make mainFrame visible when this frame is closed
        addReviewFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                showMainWindow(); // Make the mainFrame visible
            }
        });

        addReviewFrame.setSize(400, 350); // Adjusted size to fit the new field
        addReviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel for the form fields
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addReviewFrame.dispose(); // Close the "Add Review" window
                showMainWindow();
            }
        });
        panel.add(backButton);

        // Username Field
        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        panel.add(usernameField);

        /* Location Field
        JTextField locationField = new JTextField();
        locationField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        locationField.setAlignmentX(Component.CENTER_ALIGNMENT);
        locationField.setBorder(BorderFactory.createTitledBorder("Location"));
        panel.add(locationField);
        */

        // Get Packages and Display in JComboBox
        var packages = new PackageData();

        // Create a combo box for selecting a package (Hotel + Airline)
        JComboBox<String> packageComboBox = new JComboBox<>();
        for (Package p : packages.getpackageList()) {
            String displayText = p.getHotel() + " + " + p.getAirline();
            packageComboBox.addItem(displayText);
        }
        packageComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        packageComboBox.setBorder(BorderFactory.createTitledBorder("Select Package"));
        panel.add(packageComboBox);

        // Rating Field
        JTextField ratingField = new JTextField();
        ratingField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        ratingField.setAlignmentX(Component.CENTER_ALIGNMENT);
        ratingField.setBorder(BorderFactory.createTitledBorder("Rating (1-5)"));
        panel.add(ratingField);

        // Review Text Area
        JTextArea reviewTextArea = new JTextArea(5, 20);
        reviewTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewTextArea.setBorder(BorderFactory.createTitledBorder("Review (100-500 characters)"));
        reviewTextArea.setLineWrap(true);
        reviewTextArea.setWrapStyleWord(true);
        reviewTextArea.setMargin(new Insets(10, 10, 10, 10));
        panel.add(new JScrollPane(reviewTextArea));

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String selectedPackage = (String) packageComboBox.getSelectedItem(); // Get the package
                String ratingText = ratingField.getText(); // Get the rating as text
                String review = reviewTextArea.getText().trim();

                try
                {
                    // Try to parse the rating as a double
                    double rating = Double.parseDouble(ratingText);
                    
                    // Validate rating range (1-5)
                    if (rating < 1 || rating > 5)
                    {
                        JOptionPane.showMessageDialog(addReviewFrame, "Please enter a rating between 1 and 5.");
                    }
                    else if (username.isEmpty() || selectedPackage.isEmpty() || review.isEmpty()) {
                        // Validate that all fields are filled
                        JOptionPane.showMessageDialog(addReviewFrame, "Please fill in all fields.");
                    }
                    else if (review.length() < 100 || review.length() > 500)
                    {
                        JOptionPane.showMessageDialog(addReviewFrame, "Review must be between 100 - 500 characters.\nCurrently: " + review.length());
                    }
                    else
                    {
                        //String location = selectedPackage.split(" \\+ ")[0]; // Get only the hotel part as location

                        // Submit the review if all inputs are valid
                        if(ReviewManager.submitReview(username, selectedPackage, review, rating))
                        {
                            JOptionPane.showMessageDialog(addReviewFrame, "Review submitted successfully!");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(addReviewFrame, "Review failed to submit.");
                        }

                        // Clear fields after submission
                        usernameField.setText("");
                        //locationField.setText("");
                        ratingField.setText("");
                        reviewTextArea.setText("");
                    }
                } 
                catch (NumberFormatException ex)
                {
                    // Handle invalid rating input
                    JOptionPane.showMessageDialog(addReviewFrame, "Please enter a valid number for the rating.");
                }
            }
        });
        panel.add(submitButton);

        addReviewFrame.add(panel);
        addReviewFrame.setVisible(true);
    }

    private static void createViewReviewsMenu()
    {
        JFrame viewReviewsFrame = new JFrame("View Reviews Menu");
        viewReviewsFrame.setSize(400, 400);  // Adjust size for the content
        viewReviewsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add a WindowListener to make mainFrame visible when this frame is closed
        viewReviewsFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                showMainWindow(); // Make the mainFrame visible
            }
        });
    
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewReviewsFrame.dispose(); // Close the "View Reviews" window
                showMainWindow();
            }
        });
        panel.add(backButton);
    
        // Rating Threshold Label and Input Field
        JPanel thresholdPanel = new JPanel();
        thresholdPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    
        JLabel thresholdLabel = new JLabel("Rating Threshold:");
        JTextField thresholdField = new JTextField(5);  // TextField for the threshold input
        thresholdPanel.add(thresholdLabel);
        thresholdPanel.add(thresholdField);

        var reviewListPanel = new JPanel();
        reviewListPanel.add(getReviewPanels(1));

        // Apply Button
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double threshold = Double.parseDouble(thresholdField.getText());
    
                    // Validate the threshold (ensure it's between 1 and 5)
                    if (threshold < 1 || threshold > 5)
                    {
                        JOptionPane.showMessageDialog(viewReviewsFrame, "Please enter a rating threshold between 1 and 5.");
                    }
                    else
                    {
                        thresholdField.setText("");
                        // Get the locations within the rating threshold
                        reviewListPanel.removeAll();
                        reviewListPanel.add(getReviewPanels(threshold));
    
                        // Revalidate and repaint to update the UI
                        reviewListPanel.revalidate();
                        reviewListPanel.repaint();
                        panel.revalidate();
                        panel.repaint();
                    }
                } catch (NumberFormatException ex) {
                    // Handle invalid input
                    JOptionPane.showMessageDialog(viewReviewsFrame, "Please enter a valid number for the rating threshold.");
                }
            }
        });
    
        thresholdPanel.add(applyButton);
        panel.add(thresholdPanel);
        panel.add(reviewListPanel);
    
        // Set the panel to the frame
        viewReviewsFrame.add(panel);
        viewReviewsFrame.setVisible(true);
    }
    
    private static JScrollPane getReviewPanels(double threshold)
    {
        // Get the locations within the rating threshold
        ArrayList<String> locations = ReviewManager.getLocationsWithinRatingThreshold(threshold);
    
        // Create a new JPanel to hold the filtered locations
        JPanel locationPanel = new JPanel();
        locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.Y_AXIS));
        locationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center the content

        // For each location, display average rating and view reviews button
        for (String location : locations) {
            double avgRating = ReviewManager.getAverageRating(location);

            // Create a label for location and average rating
            JLabel avgRatingLabel = new JLabel("Location: " + location + " | Avg Rating: " + avgRating);
            avgRatingLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
            locationPanel.add(avgRatingLabel);

            // Create a button for viewing reviews
            JButton viewReviewsButton = new JButton("View Reviews for " + location);
            viewReviewsButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
            viewReviewsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    var reviewsForLocation = ReviewManager.getReviewsForLocation(location);
                    displayReviews(reviewsForLocation);
                }
            });
            locationPanel.add(viewReviewsButton);
        }

        // Add the list of locations to a JScrollPane to make it scrollable
        var scrollableList = new JScrollPane(locationPanel);
        scrollableList.setPreferredSize(new Dimension(350, 200)); // Set preferred size for scrollable list

        // Check if the panel has any reviews (check if it has components)
        if (locations.size() < 1)
        {
            // If there are no components, set the scroll pane to show a "No Reviews" message
            JLabel noReviewsLabel = new JLabel("No reviews match the criteria.");
            noReviewsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            locationPanel.add(noReviewsLabel);
            locationPanel.revalidate();  // Revalidate the panel layout to update
            locationPanel.repaint();  // Repaint to reflect the changes
        }
        
        return scrollableList;
    }

    private static void displayReviews(ArrayList<Review> reviews)
    {
        // Create a new dialog
        JDialog reviewDialog = new JDialog();
        reviewDialog.setTitle("View All Reviews");
        reviewDialog.setSize(400, 400);
        reviewDialog.setModal(true); // Makes the dialog modal (blocks interaction with other windows)
    
        // Create a panel to hold the reviews
        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
    
        // Add each review to the panel
        for (Review review : reviews) {
            // Display the username and rating
            JLabel userRatingLabel = new JLabel("Username: " + review.getReviewer() + " | Rating: " + review.getRating());
            userRatingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            reviewPanel.add(userRatingLabel);
    
            // Display the review text
            JTextArea reviewTextArea = new JTextArea(review.getReview());
            reviewTextArea.setEditable(false); // Make the text area read-only
            reviewTextArea.setLineWrap(true);  // Enable line wrapping
            reviewTextArea.setWrapStyleWord(true);
            reviewTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
    
            // Add the review text area to the panel (inside a scrollable view)
            JScrollPane reviewScrollPane = new JScrollPane(reviewTextArea);
            reviewScrollPane.setPreferredSize(new Dimension(350, 100)); // Limit size for each review
            reviewPanel.add(reviewScrollPane);
    
            // Add a separator for clarity between reviews
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            reviewPanel.add(separator);
        }
    
        // Add the review panel to a scrollable pane
        JScrollPane scrollPane = new JScrollPane(reviewPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
        // Add the scroll pane to the dialog
        reviewDialog.add(scrollPane);
    
        // Show the dialog
        reviewDialog.setVisible(true);
    }

    private static void hideMainWindow()
    {
        //mainFrame.dispose();
        mainFrame.setVisible(false);
    }

    private static void showMainWindow()
    {
        mainFrame.setVisible(true);
    }
}
