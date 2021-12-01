package user;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import fileio.UserInputData;

public final class User {
    private final String username;
    private final String subscriptionType;
    private final Map<String, Integer> history;
    private final ArrayList<String> favouriteVideos;
    private final Map<String, ArrayList<Integer>> ratedVideos;
    private int nrRatings;

    public User(final User user) {
        this.username = user.username;
        this.subscriptionType = user.subscriptionType;
        this.history = new HashMap<>();
        this.history.putAll(user.history);
        this.favouriteVideos = user.favouriteVideos;
        this.ratedVideos = new HashMap<>();
        this.ratedVideos.putAll(user.ratedVideos);
        this.nrRatings = user.nrRatings;
    }

    public User(final UserInputData user) {
        username = user.getUsername();
        subscriptionType = user.getSubscriptionType();
        history = user.getHistory();
        favouriteVideos = user.getFavoriteMovies();
        ratedVideos = new HashMap<>();
        nrRatings = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public ArrayList<String> getFavouriteVideos() {
        return favouriteVideos;
    }

    public Map<String, ArrayList<Integer>> getRatedVideos() {
        return ratedVideos;
    }

    public int getNrRatings() {
        return nrRatings;
    }

    public void setNrRatings(final int nrRatings) {
        this.nrRatings = nrRatings;
    }
}
