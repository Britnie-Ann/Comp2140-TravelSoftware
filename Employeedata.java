import java.io.*;
import java.util.ArrayList;

public class Employeedata {
    private ArrayList<EmployeeAccount> employeeList;

    /**
     * 
     * @param filename
     * @param delimiter
     */
    public Employeedata(){
        this.employeeList = new ArrayList<>();

        File file = new File("C:\\Users\\azool\\Documents\\TCompP\\src\\EmployeeFile");
        if (file.exists()){
            loadContactsFromFile();
        } 
    }

    private void loadContactsFromFile() {
        File file = new File(
            "C:\\Users\\azool\\Documents\\TCompP\\src\\EmployeeFile");
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] details = line.split(String.valueOf("-"));

            // Ensure that we have enough details to create a Employee object
            if (details.length >= 6) {
                // Extract mandatory fields
                String fname = details[0];
                String lname = details[1];
                String dob = details[2];
                String email = details[3];
                String username = details[4];
                String password = details[5];
                
                
                // Create a new employee object using the constructor
                EmployeeAccount employee = new EmployeeAccount(fname, lname, lname, dob, email, username, password); 

                // Add the fully populated employee object to an employee list
                employeeList.add(employee);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    //Return list of employess
    public ArrayList<EmployeeAccount> getemployeeList() {
        return employeeList;
    }
}
