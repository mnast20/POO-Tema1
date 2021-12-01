package entertainment;

import fileio.MovieInputData;

import java.util.ArrayList;

public final class Movie extends Video {
    private final int duration;
    private final ArrayList<Double> ratings;


    public Movie(final Movie movie) {
        super(movie);
        this.duration = movie.duration;
        this.ratings = new ArrayList<>();
        this.ratings.addAll(movie.ratings);
    }

    public Movie(final MovieInputData movieData) {
        super(movieData);
        duration = movieData.getDuration();
        ratings = new ArrayList<>();
    }

    public Movie copy() {
        return new Movie(this);
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }

    public double calculateRating() {
        double averageRating = 0;

        for (Double rating: ratings) {
            averageRating += rating;
        }

        return averageRating / ratings.size();
    }
}
