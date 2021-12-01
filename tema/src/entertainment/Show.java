package entertainment;

import fileio.SerialInputData;

import java.util.ArrayList;

public final class Show extends Video {
    private final int numberOfSeasons;
    private final ArrayList<Season> seasons;

    /**
     * Show copy constructor based on another show
     */
    public Show(final Show show) {
        super(show);
        this.numberOfSeasons = show.numberOfSeasons;
        this.seasons = new ArrayList<>();
        this.seasons.addAll(show.seasons);
        this.setDuration(calculateDuration());
    }

    /**
     * Show copy constructor based on input show data
     */
    public Show(final SerialInputData showData) {
        super(showData);
        numberOfSeasons = showData.getNumberSeason();
        seasons = showData.getSeasons();
    }

    /**
     * Method copying a show
     */
    public Show copy() {
        return new Show(this);
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * Method calculating average rating of show
     */
    public double calculateRating() {
        double averageRating = 0;

        for (Season season: seasons) {
            double seasonRating = 0;

            if (season.getRatings().size() > 0) {
                // calculate season rating
                for (Double rating : season.getRatings()) {
                    seasonRating += rating;
                }
                // calculate sum of seasons' average ratings
                averageRating += seasonRating / season.getRatings().size();
            }
        }

        // value of average rating
        return averageRating / numberOfSeasons;
    }

    /**
     * Method calculating the duration of a show
     */
    public int calculateDuration() {
        int length = 0;
        // calculate sum of season ratings
        for (Season season : seasons) {
            length += season.getDuration();
        }
        return length;
    }

    /**
     * Method adding rating to show's season
     */
    public void addRating(final Double rating, final Integer seasonNumber) {
        Season season = seasons.get(seasonNumber - 1);
        season.getRatings().add(rating);
    }
}
