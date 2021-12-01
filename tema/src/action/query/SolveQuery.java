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
    /**
     * Method for applying filters and solving any type of Query
     */
    public Result solveQuery(final ActionInputData query) {
        String message = "Query result: ";

        if (query.getObjectType().compareTo("actors") == 0) {
            // the query is applicable only for actors
            int i = 2;
            // get the keywords list for filtering the actors
            List<String> words = query.getFilters().get(i);
            // get the awards list for filtering the actors
            List<String> awards = query.getFilters().get(i + 1);
            // before applying any filter
            ArrayList<Actor> filter = Database.getDatabase().getAllActors();
            ArrayList<ActorsAwards> requiredAwards = new ArrayList<>();

            if (words != null) {
                // filter out actors without requested keywords in their description
                filter = new Filter().wordsFilter((ArrayList<String>) words, filter);
            }
            if (awards != null) {
                // convert the Awards string list into an ActorAwards list
                for (String award : awards) {
                    ActorsAwards value = ActorsAwards.valueOf(award);
                    requiredAwards.add(value);
                }
                // filter out actors that didn't receive requested awards
                filter = new Filter().awardsFilter(requiredAwards, filter);
            }

            if (query.getCriteria().compareTo("average") == 0) {
                // get the Average query message
                message += new Query().average(query.getNumber(), query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("awards") == 0) {
                // get the Awards query message
                message += new Query().awards(query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("filter_description") == 0) {
                // get the Filter Description query message
                message +=
                        new Query().filterDescription(query.getSortType(), filter);
            }
        } else if (query.getObjectType().compareTo("users") == 0) {
            // the query is applicable only for users
            ArrayList<User> allUsers = Database.getDatabase().getAllUsers();

            if (query.getCriteria().compareTo("num_ratings") == 0) {
                // get the Number of Ratings query message
                message +=
                        new Query().numberOfRatings(query.getNumber(),
                                query.getSortType(), allUsers);
            }
        } else {
            ArrayList<Video> filter = new ArrayList<>();

            if (query.getObjectType().compareTo("movies") == 0) {
                // the query is applicable only for movies
                filter = Database.getDatabase().getAllMovies();
            } else if (query.getObjectType().compareTo("shows") == 0) {
                // the query is applicable only for shows
                filter = Database.getDatabase().getAllShows();
            }
            // get the years list for filtering the movies/shows
            ArrayList<String> years = (ArrayList<String>) query.getFilters().get(0);
            // get the genres list for filtering the movies/shows
            ArrayList<String> genres = (ArrayList<String>) query.getFilters().get(1);

            if (years.size() != 0 && years.get(0) != null) {
                // filter out the movies/shows that weren't made in specified years
                filter = new Filter().yearFilter(years, filter);
            }
            if (genres.size() != 0 && genres.get(0) != null) {
                // filter out the movies/shows that don't include the specified genres
                filter = new Filter().genreFilter(genres, filter);
            }
            if (query.getCriteria().compareTo("ratings") == 0) {
                // get the Rating query message
                message += new Query().rating(query.getNumber(), query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("favorite") == 0) {
                // get the Favorite query message
                message += new Query().favourite(query.getNumber(), query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("longest") == 0) {
                // get the Longest query message
                message += new Query().longest(query.getNumber(), query.getSortType(), filter);
            } else if (query.getCriteria().compareTo("most_viewed") == 0) {
                // get the Most Viewed query message
                message += new Query().mostViewed(query.getNumber(), query.getSortType(), filter);
            }
        }

        // put the message and the action's id in the result
        return new Result(query.getActionId(), message);
    }
}
