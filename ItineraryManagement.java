import java.util.Scanner;

public class ItineraryManagement {
    private static Scanner scan = new Scanner(System.in);

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
        System.out.println("Select the package you are interested in");
        
    }
}
