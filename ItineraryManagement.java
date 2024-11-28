import java.util.ArrayList;
import java.util.Scanner;

public class ItineraryManagement {
    private static Scanner scan = new Scanner(System.in);
    private static PackageData data = new PackageData();
    private static ArrayList<Package> packageList = data.getpackageList();

    public static void start(){
        System.out.println("Would you like to customise Itinerary or select a Package? (Please enter to sorresponding number)");
        System.out.println("1. Customize");
        System.out.println("2. Package");
        int option = scan.nextInt();

        if (option == 1){
            CustomizedItinerary();
        }
        else if (option == 2){
            PackagedItinerary();
        }
    }

    public static void CustomizedItinerary(){
        System.out.println("Please enter trip details");

    }

    public static void PackagedItinerary(){
        System.out.println("**********************************");
        int num = 0;
        for (Package trips: packageList){
            System.out.println("\n Package " + num);
            System.out.println(trips.displaypackage());
            num ++;
        }
        System.out.println("*****************************************");
        System.out.println("Select the package you are interested in");
        int number = scan.nextInt();

        packageList.get(number).displaypackage();
    }
}
