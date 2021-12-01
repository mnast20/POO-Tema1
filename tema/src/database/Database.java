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
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Video> videos = new ArrayList<>();
    private final ArrayList<Actor> actors = new ArrayList<>();

    private static Database instance;

    private Database() { }

    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void addActors(final List<ActorInputData> actorsData) {
        for (ActorInputData actor: actorsData) {
            actors.add(new Actor(actor));
        }
    }

    public void addUsers(final List<UserInputData> usersData) {
        for (UserInputData user: usersData) {
            users.add(new User(user));
        }
    }

    public void addVideos(final List<MovieInputData> moviesData,
                          final List<SerialInputData> serialsData) {
        for (MovieInputData movie: moviesData) {
            videos.add(new Movie(movie));
        }
        for (SerialInputData show: serialsData) {
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

    public ArrayList<Video> getAllMovies() {
        ArrayList<Video> moviesInDatabase = new ArrayList<>();

        for (Video video: videos) {
            if (video instanceof Movie) {
                moviesInDatabase.add(video);
            }
        }

        return moviesInDatabase;
    }

    public ArrayList<Video> getAllShows() {
        ArrayList<Video> moviesInDatabase = new ArrayList<>();

        for (Video video: videos) {
            if (video instanceof Show) {
                moviesInDatabase.add(video);
            }
        }

        return moviesInDatabase;
    }

    public void clear() {
        instance = null;
    }
}
