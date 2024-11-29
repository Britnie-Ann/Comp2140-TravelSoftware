import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ReviewManager
{
    public static List<Review> Reviews = new ArrayList<Review>();

    public static boolean submitReview(String username, String location, String review, double rating)
    {
        if (review.length() < 100)
        {
            return false;
        }
        else if (review.length() > 500)
        {
            return false;
        }
        else
        {
            Review temp = new Review(username, location, review, rating);
            Reviews.add(temp);
            return true;
        }
    }

    public static ArrayList<Review> getReviewsForLocation(String location)
    {
        var temp = new ArrayList<Review>();

        for (Review review : Reviews)
        {
            if(review.getLocation().toLowerCase().equals(location.toLowerCase()))
            {
                temp.add(review);
            }
        }

        return temp;
    }

    public static double getAverageRating(String location)
    {
        double ratings = 0;
        double sum = 0;

        for (var rating : Reviews)
        {
            if (rating.getLocation().toLowerCase().equals(location.toLowerCase()))
            {
                sum += rating.getRating();
                ratings++;
            }
        }

        double average = ratings > 0 ? sum / ratings : 0;
        return average;
    }

    public static ArrayList<String> getLocationsWithinRatingThreshold(double threshold)
    {
        ArrayList<String> locations = new ArrayList<String>();
        HashSet<String> uniqueLocations = new HashSet<>();

        for (var rating : Reviews)
        {
            String location = rating.getLocation().toLowerCase();

            if (!uniqueLocations.contains(rating.getLocation().toLowerCase()))
            {
                uniqueLocations.add(location);
            }
        }

        for (String location : uniqueLocations)
        {
            double rating = getAverageRating(location);

            if(rating >= threshold)
            {
                locations.add(location);
            }
        }

        return locations;
    }
}
