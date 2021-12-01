package action.query;

import actor.Actor;
import actor.ActorsAwards;
import database.Database;
import entertainment.Video;
import fileio.ActionInputData;
import result.Result;
import user.User;

import java.util.ArrayList;
import java.util.List;

public final class SolveQuery {
    public Result solveQuery(final ActionInputData query) {
        String message = "Query result: ";

        if (query.getObjectType().compareTo("actors") == 0) {
            int i = 2;
            List<String> words = query.getFilters().get(i);
            List<String> awards = query.getFilters().get(i + 1);
            ArrayList<Actor> filter = Database.getDatabase().getAllActors();
            ArrayList<ActorsAwards> requiredAwards = new ArrayList<>();

            if (words != null) {
                filter = new Filter().wordsFilter((ArrayList<String>) words, filter);
            }
            if (awards != null) {
                for (String award : awards) {
                    ActorsAwards value = ActorsAwards.valueOf(award);
                    requiredAwards.add(value);
                }
                filter = new Filter().awardsFilter(requiredAwards, filter);
            }

            if (query.getCriteria().compareTo("average") == 0) {
                message += new Query().average(query.getNumber(), query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("awards") == 0) {
                if (words != null) {
                    filter = new Filter().wordsFilter((ArrayList<String>) words, filter);
                }
                message += new Query().awards(query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("filter_description") == 0) {
                message +=
                        new Query().filterDescription(query.getSortType(), filter);
            }
        } else if (query.getObjectType().compareTo("users") == 0) {
            ArrayList<User> allUsers = Database.getDatabase().getAllUsers();

            if (query.getCriteria().compareTo("num_ratings") == 0) {
                message +=
                        new Query().numberOfRatings(query.getNumber(),
                                query.getSortType(), allUsers);
            }
        } else {
            ArrayList<Video> filter = new ArrayList<>();

            if (query.getObjectType().compareTo("movies") == 0) {
                filter = Database.getDatabase().getAllMovies();
            } else if (query.getObjectType().compareTo("shows") == 0) {
                filter = Database.getDatabase().getAllShows();
            }
            ArrayList<String> years = (ArrayList<String>) query.getFilters().get(0);
            ArrayList<String> genres = (ArrayList<String>) query.getFilters().get(1);

            if (years.size() != 0 && years.get(0) != null) {
                filter = new Filter().yearFilter(years, filter);
            }
            if (genres.size() != 0 && genres.get(0) != null) {
                filter = new Filter().genreFilter(genres, filter);
            }
            if (query.getCriteria().compareTo("ratings") == 0) {
                message += new Query().rating(query.getNumber(), query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("favorite") == 0) {
                message += new Query().favourite(query.getNumber(), query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("longest") == 0) {
                message += new Query().longest(query.getNumber(), query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("most_viewed") == 0) {
                message += new Query().mostViewed(query.getNumber(), query.getSortType(), filter);
            }
        }

        return new Result(query.getActionId(), message);
    }
}
