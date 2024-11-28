import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PackageData {
    private ArrayList<Package> packageList;

    public PackageData(){
        this.packageList = new ArrayList<>();

        File file = new File("C:\\Users\\azool\\Documents\\TCompP\\src\\PackagesFile");
        if (file.exists()){
            PackageDataload();
        } 
    }

    private void PackageDataload(){
        //File containing some package informations
        File file = new File(
            "C:\\Users\\azool\\Documents\\TCompP\\src\\PackagesFile");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
            String[] details = line.split(String.valueOf("-"));

            // Ensure that we have enough details to create a package object
            if (details.length >= 6) {
                // Extract mandatory fields
                String country = details[0];
                String Hotel = details[1];
                String DOA = details[2];
                String DOD = details[3];
                String Airline = details[4];
                String Price = details[5];
                
                
                // Create a package
                Package trip = new Package(country, Hotel, DOA, DOD, Airline, Price); 

                // Add the fully populated package object to packages list
                packageList.add(trip);
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * @return the list of existing packages
     */
    public ArrayList<Package> getpackageList() {
        return packageList;
    }
}