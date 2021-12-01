package database;

import actor.Actor;
import entertainment.Video;
import entertainment.Movie;
import entertainment.Show;
import fileio.UserInputData;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import user.User;

import java.util.ArrayList;
import java.util.List;

public final class Database {
    private final ArrayList<User> users = new ArrayList<>(); // all users
    private final ArrayList<Video> videos = new ArrayList<>(); // all videos
    private final ArrayList<Actor> actors = new ArrayList<>(); // all actors

    // Lazy Singleton Database
    private static Database instance;

    private Database() { }

    /**
     * Method returning Database instance
     */
    public static Database getDatabase() {
        if (instance == null) {
            // recreate Database
            instance = new Database();
        }
        return instance;
    }

    /**
     * Method adding actors to Database
     */
    public void addActors(final List<ActorInputData> actorsData) {
        for (ActorInputData actor: actorsData) {
            actors.add(new Actor(actor));
        }
    }

    /**
     * Method adding users to Database
     */
    public void addUsers(final List<UserInputData> usersData) {
        for (UserInputData user: usersData) {
            users.add(new User(user));
        }
    }

    /**
     * Method adding videos to Database
     */
    public void addVideos(final List<MovieInputData> moviesData,
                          final List<SerialInputData> serialsData) {
        for (MovieInputData movie: moviesData) {
            // add the movies first in the video list
            videos.add(new Movie(movie));
        }
        for (SerialInputData show: serialsData) {
            // add the shows second in the video list
            videos.add(new Show(show));
        }
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public ArrayList<Video> getAllVideos() {
        return videos;
    }

    public ArrayList<Actor> getAllActors() {
        return actors;
    }

    /**
     * Method returning all movies from the video list
     */
    public ArrayList<Video> getAllMovies() {
        ArrayList<Video> moviesInDatabase = new ArrayList<>();

        for (Video video: videos) {
            // check if video is a movie instance
            if (video instanceof Movie) {
                // add movie to list
                moviesInDatabase.add(video);
            }
        }

        return moviesInDatabase;
    }

    /**
     * Method returning all shows from the video list
     */
    public ArrayList<Video> getAllShows() {
        ArrayList<Video> moviesInDatabase = new ArrayList<>();

        for (Video video: videos) {
            // check if video is a show instance
            if (video instanceof Show) {
                // add show to list
                moviesInDatabase.add(video);
            }
        }

        return moviesInDatabase;
    }

    /**
     * Method declaring database as empty, clearing it
     */
    public void clear() {
        instance = null;
    }
}
