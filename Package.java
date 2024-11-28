
public class Package{
    private String country;
    private String Hname; //Hotel Name
    private String DOA; // Date of Arrival
    private String DOD; //Date of departure
    private String Airline; 
    private String price; 

    public Package(String country, String Hname, String DOA, String DOD, String Airline, String price){
        this.country = country;
        this.Hname = Hname;
        this.DOA = DOA;
        this.DOD = DOD;
        this.Airline = Airline;
        this.price = price;
    }

    //Getter methods
    public String getCountry(){
        return country;
    }

    public String getHotel(){
        return Hname;
    }

    public String getDOA(){
        return DOA;
    }

    public String getDOD(){
        return DOD;
    }

    public String getAirline(){
        return Airline;
    }

    public String getprice(){
        return price;
    }

    public String displaypackage(){
        return "\n Country: " + country +
                "\n Hotel: " + Hname +
                "\n Date of Arrival: " + DOA +
                "\n Date of Departure: " + DOD +
                "n Airline: " + Airline +
                "\n Price: " + price;
    }
}