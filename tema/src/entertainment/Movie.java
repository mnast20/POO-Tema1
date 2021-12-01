package entertainment;

import fileio.MovieInputData;

import java.util.ArrayList;

public final class Movie extends Video {
    private final int duration;
    private final ArrayList<Double> ratings;

    /**
     * Movie copy constructor based on another movie
     */
    public Movie(final Movie movie) {
        super(movie);
        this.duration = movie.duration;
        this.ratings = new ArrayList<>();
        this.ratings.addAll(movie.ratings);
    }

    /**
     * Movie copy constructor based on input movie data
     */
    public Movie(final MovieInputData movieData) {
        super(movieData);
        duration = movieData.getDuration();
        ratings = new ArrayList<>();
    }

    /**
     * Method copying a movie
     */
    public Movie copy() {
        return new Movie(this);
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }

    /**
     * Method calculating average rating of movie
     */
    public double calculateRating() {
        double averageRating = 0;

        for (Double rating: ratings) {
            // calculate sum of ratings
            averageRating += rating;
        }

        // value of average rating
        return averageRating / ratings.size();
    }
}
