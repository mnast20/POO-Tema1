package entertainment;

import fileio.SerialInputData;

import java.util.ArrayList;

public final class Show extends Video {
    private final int numberOfSeasons;
    private final ArrayList<Season> seasons;

    public Show(final Show show) {
        super(show);
        this.numberOfSeasons = show.numberOfSeasons;
        this.seasons = new ArrayList<>();
        this.seasons.addAll(show.seasons);
    }

    public Show(final SerialInputData showData) {
        super(showData);
        numberOfSeasons = showData.getNumberSeason();
        seasons = showData.getSeasons();
    }

    public Show copy() {
        return new Show(this);
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public double calculateRating() {
        double averageRating = 0;

        for (Season season: seasons) {
            double seasonRating = 0;

            if (season.getRatings().size() > 0) {
                for (Double rating : season.getRatings()) {
                    seasonRating += rating;
                }
                averageRating += seasonRating / season.getRatings().size();
            }
        }

        return averageRating / numberOfSeasons;
    }

    public int getDuration() {
        int length = 0;
        for (Season season : seasons) {
            length += season.getDuration();
        }
        return length;
    }

    public void addRating(final Double rating, final Integer seasonNumber) {
        Season season = seasons.get(seasonNumber - 1);
        season.getRatings().add(rating);
    }
}
