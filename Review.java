public class Review
{
    private String reviewer;
    private String location;
    private String review;
    private double rating;

    public Review(String reviewer, String location, String review, double rating)
    {
        this.reviewer = reviewer;
        this.location = location;
        this.review = review;
        setRating(rating);
    }

    public String getReviewer()
    {
        return reviewer;
    }

    public String getReview()
    {
        return review;
    }

    public String getLocation()
    {
        return location;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
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
        return "Review:\n" +
            "Reviewer: " + reviewer + "\n" +
            "Location: " + location + "\n" +
            "Review: " + review + "\n" +
            "Rating: " + rating + "\n";
    }
}
