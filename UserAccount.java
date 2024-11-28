public class UserAccount {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String telephoneNumber;
    private String emailAddress;
    private String continentPreference;
    private String countryPreference;
    private String username;
    private String password;
    

    // Constructor
    public UserAccount(String firstName, String lastName, String dateOfBirth, String telephoneNumber,
                    String emailAddress, String username, String password,
                       String continentPreference, String countryPreference) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.continentPreference = continentPreference;
        this.countryPreference = countryPreference;
    }

    // Getter methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getContinentPreference() {
        return continentPreference;
    }

    public String getCountryPreference() {
        return countryPreference;
    }

    // Setter methods
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContinentPreference(String continentPreference) {
        this.continentPreference = continentPreference;
    }

    public void setCountryPreference(String countryPreference) {
        this.countryPreference = countryPreference;
    }
}