import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ReviewManager
{
    public static List<Review> Reviews = new ArrayList<Review>();
    public static List<Rating> Ratings = new ArrayList<Rating>();

    public static void submitRating(String username, String location, int rating)
    {
        Rating newRating = new Rating(username, location, rating);
        Ratings.add(newRating);
    }

    public static boolean submitReview(String reviewer, String location, String review)
    {
        Review newReview = new Review(reviewer, location, review);

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
            Reviews.add(newReview);
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

    public static ArrayList<Rating> getRatingsForLocation(String location)
    {
        var temp = new ArrayList<Rating>();

        for (Rating rating : Ratings)
        {
            if(rating.getLocation().toLowerCase().equals(location.toLowerCase()))
            {
                temp.add(rating);
            }
        }

        return temp;
    }

    public static int getAverageRating(String location)
    {
        int ratings = 0;
        int sum = 0;

        for (Rating rating : Ratings)
        {
            if (rating.getLocation().equals(location))
            {
                sum += rating.getRating();
                ratings++;
            }
        }

        int average = ratings > 0 ? sum / ratings : 0;
        return average;
    }

    public static ArrayList<String> getLocationsWithinThreshold(int threshold)
    {
        ArrayList<String> locations = new ArrayList<String>();
        HashSet<String> uniqueLocations = new HashSet<>();

        for (Rating rating : Ratings)
        {
            String location = rating.getLocation().toLowerCase();

            if (!uniqueLocations.contains(rating.getLocation().toLowerCase()))
            {
                uniqueLocations.add(location);
                uniqueLocations.add(location);
            }
        }

        for (String location : uniqueLocations)
        {
            int rating = getAverageRating(location);

            if(rating >= threshold)
            {
                locations.add(location);
            }
        }

        return locations;
    }
}

class Rating
{
    private String reviewer;
    private int rating;
    private String location;

    public Rating(String reviewer, String location, int rating)
    {
        this.reviewer = reviewer;
        this.location = location;
        setRating(rating);
    }

    public String getReviewer()
    {
        return reviewer;
    }

    public String getLocation()
    {
        return location;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = 0;

        if (rating > 5)
        {
            this.rating = 5;
        }
        else if (rating < 1)
        {
            this.rating = 1;
        }
        else
        {
            this.rating = rating;
        }
    }

    @Override
    public String toString()
    {
        return "Rating:\n" +
            "Reviewer: " + reviewer + "\n" +
            "Location: " + location + "\n" +
            "Rating: " + rating + "\n";
    }

}

class Review
{
    private String reviewer;
    private String review;
    private String location;

    public Review(String reviewer, String location, String review)
    {
        this.reviewer = reviewer;
        this.location = location;
        this.review = review;
    }

    public String getReviewer()
    {
        return review;
    }

    public String getReview()
    {
        return review;
    }

    public String getLocation()
    {
        return location;
    }

    @Override
    public String toString()
    {
        return "Review:\n" +
            "Reviewer: " + reviewer + "\n" +
            "Location: " + location + "\n" +
            "Review: " + review + "\n";
    }
}
