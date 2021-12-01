package entertainment;

import database.Database;
import fileio.ShowInput;
import user.User;

import java.util.ArrayList;
import java.util.Map;

public class Video {
    private final String title;
    private final int year;
    private final ArrayList<String> genres;
    private final ArrayList<String> cast;
    private double rating;
    private int nrViews;
    private int nrFavourites;

    /**
     * Video copy constructor based on another video
     */
    public Video(final Video video) {
        this.title = video.title;
        this.year = video.year;
        this.cast = new ArrayList<>();
        cast.addAll(video.cast);
        this.genres = new ArrayList<>();
        genres.addAll(video.genres);
        rating = video.rating;
        nrViews = video.nrViews;
        nrFavourites = video.nrFavourites;
    }

    /**
     * Video copy constructor based on video input data
     */
    public Video(final ShowInput videoData) {
        this.title = videoData.getTitle();
        this.year = videoData.getYear();
        this.cast = videoData.getCast();
        this.genres = videoData.getGenres();
        rating = 0;
        nrViews = 0;
        nrFavourites = 0;
    }

    /**
     * Method copying a video
     */
    public Video copy() {
        return new Video(this);
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    public final double getRating() {
        return rating;
    }

    public final int getNrViews() {
        return nrViews;
    }

    public final int getNrFavourites() {
        return nrFavourites;
    }

    public final void setRating(final double rating) {
        this.rating = rating;
    }

    public final void setNrViews(final int nrViews) {
        this.nrViews = nrViews;
    }

    public final void setNrFavourites(final int nrFavourites) {
        this.nrFavourites = nrFavourites;
    }

    /**
     * Method returning the duration of a video
     */
    public int getDuration() {
        return 0;
    }

    /**
     * Method calculating the average rating of a video
     */
    public double calculateRating() {
        return 0;
    }

    /**
     * Method checking if video has all required genres
     */
    public boolean checkVideoGenres(final ArrayList<String> requiredGenres) {
        int nr = 0;

        for (String genre: genres) {
            for (String requiredGenre: requiredGenres) {
                // check if video genre is among the required genres
                if (genre.compareTo(requiredGenre) == 0) {
                    nr++;
                    break;
                }
            }
            if (nr == requiredGenres.size()) {
                // all required genres were found
                return true;
            }
        }

        return false;
    }

    /**
     * Method calculating video views
     */
    public int calculateVideoViews() {
        int nrOfViews = 0;
        ArrayList<User> users = Database.getDatabase().getAllUsers();

        // calculate sum of video views
        for (User user: users) {
            Map<String, Integer> history = user.getHistory();
            // get user's video views
            Integer nrViewsUser = history.get(title);

            if (nrViewsUser != null) {
                nrOfViews += history.get(title);
            }
        }

        return nrOfViews;
    }
}
