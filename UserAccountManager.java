import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserAccountManager {

    private static List<UserAccount> userAccounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Method to prompt the user for account creation or login
    public static void start() {
        System.out.println("Welcome to the Travel Account System!");
        System.out.println("1. Create a New Account");
        System.out.println("2. Log In to Existing Account");
        System.out.print("Please choose an option (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            createUserAccount();
        } else if (choice == 2) {
            loginUserAccount();
        } else {
            System.out.println("Invalid choice. Please try again.");
            start();
        }
    }

    // Method to create a new user account
    public static void createUserAccount() {
        System.out.println("\nPlease enter the following information to create your account:");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        String dateOfBirth;
        do {
            System.out.print("Date of Birth (dd/mm/yyyy): ");
            dateOfBirth = scanner.nextLine();
        } while (!Validator.isValidDateOfBirth(dateOfBirth));

        String telephoneNumber;
        do {
            System.out.print("Telephone Number: ");
            telephoneNumber = scanner.nextLine();
        } while (!Validator.isValidPhoneNumber(telephoneNumber));

        System.out.print("Secondary Telephone Number: ");
        String secondaryTelephoneNumber = scanner.nextLine();

        String emailAddress;
        do {
            System.out.print("Email Address: ");
            emailAddress = scanner.nextLine();
        } while (!Validator.isValidEmail(emailAddress));

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Select continent and country
        System.out.println("Please select your continent preference:");
        System.out.println("1. North America\n2. South America/Caribbean\n3. Africa\n4. Europe\n5. Asia\n6. Australia");
        int continentChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String continentPreference = getContinentPreference(continentChoice);

        System.out.println("You selected " + continentPreference + ". Here are the country options:");
        String countryPreference = getCountryPreference(continentChoice);

        System.out.print("Would you like a full package or partial package deal? ");
        String packageType = scanner.nextLine();

        System.out.println("Accommodation preference:");
        System.out.println("1. Hotel Stay\n2. Airbnb");
        int accommodationChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String accommodationPreference = (accommodationChoice == 1) ? "Hotel Stay" : "Airbnb";

        UserAccount newUser = new UserAccount(firstName, lastName, dateOfBirth, telephoneNumber,
                secondaryTelephoneNumber, emailAddress, username, password, continentPreference,
                countryPreference, packageType, accommodationPreference);

        userAccounts.add(newUser);
        System.out.println("Account created successfully!\n");
        displayUserInfo(newUser);

        // Ask if the user wants to update or delete the account
        askForUpdateOrDelete(newUser);
    }

    // Method for logging into an existing account
    public static void loginUserAccount() {
        System.out.println("\nPlease log in with your username and password.");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean found = false;
        for (UserAccount account : userAccounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                found = true;
                System.out.println("\nLogin successful! Welcome back, " + account.getFirstName() + " " + account.getLastName());
                displayUserInfo(account);
                askForUpdateOrDelete(account); // Prompt to update or delete after successful login
                break;
            }
        }

        if (!found) {
            System.out.println("Incorrect username or password. Please try again.");
            loginUserAccount(); // Allow the user to try again
        }
    }

    // Ask the user if they want to update or delete their account
    private static void askForUpdateOrDelete(UserAccount user) {
        System.out.println("\nWould you like to update or delete your account?");
        System.out.println("1. Update Account");
        System.out.println("2. Delete Account");
        System.out.println("3. Exit");
        System.out.print("Please choose an option (1, 2, or 3): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            updateUserAccount(user);
        } else if (choice == 2) {
            deleteUserAccount(user);
        } else {
            System.out.println("Thank you for using the Travel Account System!");
        }
    }

    // Method to update user account
    private static void updateUserAccount(UserAccount user) {
        System.out.println("\nEnter the detail you want to update:");
        System.out.println("1. First Name\n2. Last Name\n3. Date of Birth\n4. Telephone Number\n5. Email Address");
        System.out.println("6. Username\n7. Password\n8. Continent Preference\n9. Country Preference");
        System.out.println("10. Package Type\n11. Accommodation Preference");
        System.out.print("Enter your choice: ");
        int updateChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new First Name: ");
                user.setFirstName(scanner.nextLine());
                break;
            case 2:
                System.out.print("Enter new Last Name: ");
                user.setLastName(scanner.nextLine());
                break;
            case 3:
                System.out.print("Enter new Date of Birth (dd/mm/yyyy): ");
                user.setDateOfBirth(scanner.nextLine());
                break;
            case 4:
                String newPhone;
                do {
                    System.out.print("Enter new Telephone Number: ");
                    newPhone = scanner.nextLine();
                } while (!Validator.isValidPhoneNumber(newPhone));
                user.setTelephoneNumber(newPhone);
                break;
            case 5:
                System.out.print("Enter new Email Address: ");
                user.setEmailAddress(scanner.nextLine());
                break;
            case 6:
                System.out.print("Enter new Username: ");
                user.setUsername(scanner.nextLine());
                break;
            case 7:
                System.out.print("Enter new Password: ");
                user.setPassword(scanner.nextLine());
                break;
            case 8:
                System.out.print("Enter new Continent Preference: ");
                user.setContinentPreference(scanner.nextLine());
                break;
            case 9:
                System.out.print("Enter new Country Preference: ");
                user.setCountryPreference(scanner.nextLine());
                break;
            case 10:
                System.out.print("Enter new Package Type: ");
                user.setPackageType(scanner.nextLine());
                break;
            case 11:
                System.out.print("Enter new Accommodation Preference: ");
                user.setAccommodationPreference(scanner.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        System.out.println("Account updated successfully!");
        displayUserInfo(user);
    }

    // Method to delete user account
    private static void deleteUserAccount(UserAccount user) {
        userAccounts.remove(user);
        System.out.println("Account deleted successfully!");
    }

    // Method to display user information
    private static void displayUserInfo(UserAccount user) {
        System.out.println("\nAccount Information:");
        System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
        System.out.println("Date of Birth: " + user.getDateOfBirth());
        System.out.println("Telephone: " + user.getTelephoneNumber());
        System.out.println("Email: " + user.getEmailAddress());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Continent Preference: " + user.getContinentPreference());
        System.out.println("Country Preference: " + user.getCountryPreference());
        System.out.println("Package Type: " + user.getPackageType());
        System.out.println("Accommodation Preference: " + user.getAccommodationPreference());
    }

    // Helper methods for continent and country selection
    private static String getContinentPreference(int continentChoice) {
        switch (continentChoice) {
            case 1: return "North America";
            case 2: return "South America/Caribbean";
            case 3: return "Africa";
            case 4: return "Europe";
            case 5: return "Asia";
            case 6: return "Australia";
            default: return "North America";
        }
    }

    private static String getCountryPreference(int continentChoice) {
        switch (continentChoice) {
            case 1: return "USA";
            case 2: return "Jamaica";
            case 3: return "South Africa";
            case 4: return "Spain";
            case 5: return "Japan";
            case 6: return "Australia";
            default: return "USA";
        }
    }
}
