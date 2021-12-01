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

    public Video(final ShowInput videoData) {
        this.title = videoData.getTitle();
        this.year = videoData.getYear();
        this.cast = videoData.getCast();
        this.genres = videoData.getGenres();
        rating = 0;
        nrViews = 0;
        nrFavourites = 0;
    }

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

    public int getDuration() {
        return 0;
    }

    public double calculateRating() {
        return 0;
    }

    public boolean checkVideoGenres(final ArrayList<String> requiredGenres) {
        int nr = 0;

        for (String genre: genres) {
            for (String requiredGenre: requiredGenres) {
                if (genre.compareTo(requiredGenre) == 0) {
                    nr++;
                    break;
                }
            }
            if (nr == requiredGenres.size()) {
                return true;
            }
        }

        return false;
    }

    public int calculateVideoViews() {
        int nrOfViews = 0;
        ArrayList<User> users = Database.getDatabase().getAllUsers();

        for (User user: users) {
            Map<String, Integer> history = user.getHistory();
            Integer nrViewsUser = history.get(title);

            if (nrViewsUser != null) {
                nrOfViews += history.get(title);
            }
        }

        return nrOfViews;
    }
}
