public class Review
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
