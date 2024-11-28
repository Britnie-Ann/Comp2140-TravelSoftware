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
