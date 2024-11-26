public class Validator {

    public static boolean isValidPhoneNumber(String telephoneNumber) {
        if (telephoneNumber.length() != 10 || !telephoneNumber.startsWith("876")) {
            System.out.println("Incorrect phone number format. It should be 10 digits and start with '876'.");
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        if (email.endsWith(".com") || email.endsWith(".edu") || email.endsWith(".org")) {
            return true;
        }
        System.out.println("Incorrect email format. Email should end with '.com', '.edu', or '.org'.");
        return false;
    }

    public static boolean isValidDateOfBirth(String dateOfBirth) {
        if (dateOfBirth.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return true;
        }
        System.out.println("Incorrect date format. Please enter in dd/mm/yyyy format.");
        return false;
    }
}