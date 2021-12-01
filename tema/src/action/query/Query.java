package action.query;

import actor.Actor;
import actor.ActorUtil;
import entertainment.Video;
import sort.SortActor;
import sort.SortUser;
import sort.SortVideo;
import user.User;

import java.util.ArrayList;


public final class Query {
    /**
     * Query sorting given list of actors by their average rating in a specified order
     * Query returns the first N names of the actors in sorted list
     */
    public ArrayList<String> average(final int n, final String sortType,
                                final ArrayList<Actor> actors) {
        // get actor names from sorted list of actors by their average rating
        ArrayList<String> actorsByRating =
                new SortActor().sortActorsByAverageRating(sortType, actors);

        // remove actors' names until the list contains N elements
        while (actorsByRating.size() > n) {
            actorsByRating.remove(actorsByRating.size() - 1);
        }

        return actorsByRating;
    }

    /**
     * Query sorting given list of actors with required awards by their total number of awards
     * in a specified order
     * Query returns the full sorted list of actors' names
     */
    public ArrayList<String> awards(final String sortType, final ArrayList<Actor> filteredActors) {
        // the provided list of actors will always be filtered based on required awards
        // get actor names from sorted list of actors(with required awards)
        // by total number of awards
        return new SortActor().sortActorsByAwards(sortType, filteredActors);
    }

    /**
     * Query sorting given list of actors with required keywords by their name in a specified order
     * Query returns the full sorted list of actors' names
     */
    public ArrayList<String> filterDescription(final String sortType,
                                               final ArrayList<Actor> filteredActors) {
        // the provided list of actors will always be filtered based on required keywords
        // get actor names from list of actors with given keywords in their career description
        ArrayList<String> actorsWithKeywords = new ActorUtil().getActorNames(filteredActors);

        // sort names alphabetically
        return new SortActor().sortActorsByName(sortType, actorsWithKeywords);
    }

    /**
     * Query sorting given list of videos by their average rating in a specified order
     * Query returns the first N videos in sorted list
     */
    public ArrayList<String> rating(final int n, final String sortType,
                                    final ArrayList<Video> videos) {
        // get video titles from list of sorted videos by average rating
        ArrayList<String> videosByRating =
                new SortVideo().sortVideosByRating(sortType, videos, 1, 0);

        // remove videos' titles until the list contains N elements
        while (videosByRating.size() > n) {
            videosByRating.remove(videosByRating.size() - 1);
        }

        return videosByRating;
    }

    /**
     * Query sorting given list of videos by their average rating in a specified order
     * Query returns the first N titles of the videos in sorted list
     */
    public ArrayList<String> favourite(final int n, final String sortType,
                                       final ArrayList<Video> videos) {
        // get video titles from sorted list of videos by their
        // number of appearances in users' favourite lists
        ArrayList<String> videosByNrFavourites =
                new SortVideo().sortVideosByFavourite(sortType, videos, 1);

        // remove videos' titles until the list contains N elements
        while (videosByNrFavourites.size() > n) {
            videosByNrFavourites.remove(videosByNrFavourites.size() - 1);
        }

        return videosByNrFavourites;
    }

    /**
     * Query sorting given list of videos by their length in a specified order
     * Query returns the first N titles of the videos in sorted list
     */
    public ArrayList<String> longest(final int n, final String sortType,
                                     final ArrayList<Video> videos) {
        // get video names from sorted list of videos by their length
        ArrayList<String> videosByLength =
                new SortVideo().sortVideosByLength(sortType, videos);

        // remove videos' titles until the list contains N elements
        while (videosByLength.size() > n) {
            videosByLength.remove(videosByLength.size() - 1);
        }

        return videosByLength;
    }

    /**
     * Query sorting given list of videos by their number of views in a specified order
     * Query returns the first N titles of the videos in sorted list
     */
    public ArrayList<String> mostViewed(final int n, final String sortType,
                                        final ArrayList<Video> videos) {
        // get video names from sorted list of videos by their number of views
        ArrayList<String> videosByViews = new SortVideo().sortVideosByViews(sortType, videos);

        // remove videos' titles until the list contains N elements
        while (videosByViews.size() > n) {
            videosByViews.remove(videosByViews.size() - 1);
        }

        return videosByViews;
    }

    /**
     * Query sorting given list of users by their number of ratings in a specified order
     * Query returns the first N usernames of the users in sorted list
     */
    public ArrayList<String> numberOfRatings(final int n, final String sortType,
                                             final ArrayList<User> users) {
        // get usernames from sorted list of users by their number of ratings
        ArrayList<String> usersByNumberRatings =
                new SortUser().sortUsersByNrOfRatings(sortType, users);

        // remove usernames until the list contains N elements
        while (usersByNumberRatings.size() > n) {
            usersByNumberRatings.remove(usersByNumberRatings.size() - 1);
        }

        return usersByNumberRatings;
    }
}
