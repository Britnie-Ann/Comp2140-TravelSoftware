import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeInterface {
    private static Scanner scan = new Scanner(System.in);
    private static PackageData data = new PackageData();
    private static ArrayList<Package> packageList = data.getpackageList();

    public static void start(){
        System.out.println("**************************************");
        System.out.println("What would you like to do?");
        System.out.println("1. Enter a new Package");
        System.out.println("2. Delete a Package");
        System.out.println("3. Exit system");
        int option = scan.nextInt();
        scan.nextLine();

        if (option == 1){
            newPackage();
        }else if (option == 2){
            deletePackage();
        }else if (option == 3){
            System.exit(0);
        }
    }

    public static void newPackage(){
        System.out.println("\n Please enter the information for the folloing fields");
        System.out.println("Country: ");
        String country = scan.nextLine();
        System.out.println("Hotel Name: ");
        String Hotel = scan.nextLine();
        System.out.println("Date of arrival to trip destination(dd/mm/yy): ");
        String DOA = scan.nextLine();
        System.out.println("Date of departure from trip destination(dd/mm/yy): ");
        String DOD = scan.nextLine();
        System.out.println("Airlines: ");
        String Airline = scan.nextLine();
        System.out.println("Price: ");
        String price = scan.nextLine();

        Package trip = new Package(country, Hotel, DOA, DOD, Airline, price); 

        // Add the fully populated package object to packages list
        packageList.add(trip);
        System.out.println("New Package added. Please review added information below");
        System.out.println(trip.displaypackage());
    }

    public static void deletePackage(){
        int num = 0;
        for (Package trips: packageList){
            System.out.println("Package " + num);
            System.out.println(trips.displaypackage());
            num ++;
        }

        System.out.println("which package would you like to delete. Please enter it's number");
        int number = scan.nextInt();
        scan.nextLine();

        packageList.remove(number);
        System.out.println("Package deleted");
    }

}
