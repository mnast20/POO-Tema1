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
    public ArrayList<String> average(final int n, final String sortType,
                                final ArrayList<Actor> actors) {
        ArrayList<String> actorsByRating =
                new SortActor().sortActorsByAverageRating(sortType, actors);

        while (actorsByRating.size() > n) {
            actorsByRating.remove(actorsByRating.size() - 1);
        }

        return actorsByRating;
    }

    public ArrayList<String> awards(final String sortType, final ArrayList<Actor> filteredActors) {
        return new SortActor().sortActorsByAwards(sortType, filteredActors);
    }

    public ArrayList<String> filterDescription(final String sortType,
                                               final ArrayList<Actor> filteredActors) {
        ArrayList<String> actorsWithKeywords = new ActorUtil().getActorNames(filteredActors);

        return new SortActor().sortActorsByName(sortType, actorsWithKeywords);
    }

    public ArrayList<String> rating(final int n, final String sortType,
                                    final ArrayList<Video> videos) {
        ArrayList<String> videosByRating =
                new SortVideo().sortVideosByRating(sortType, videos, 1, 0);

        while (videosByRating.size() > n) {
            videosByRating.remove(videosByRating.size() - 1);
        }

        return videosByRating;
    }

    public ArrayList<String> favourite(final int n, final String sortType,
                                       final ArrayList<Video> videos) {
        ArrayList<String> videosByNrFavourites =
                new SortVideo().sortVideosByFavourite(sortType, videos, 1);

        while (videosByNrFavourites.size() > n) {
            videosByNrFavourites.remove(videosByNrFavourites.size() - 1);
        }

        return videosByNrFavourites;
    }

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

    public ArrayList<String> mostViewed(final int n, final String sortType,
                                        final ArrayList<Video> videos) {
        ArrayList<String> videosByViews = new SortVideo().sortVideosByViews(sortType, videos);

        while (videosByViews.size() > n) {
            videosByViews.remove(videosByViews.size() - 1);
        }

        return videosByViews;
    }

    public ArrayList<String> numberOfRatings(final int n, final String sortType,
                                             final ArrayList<User> users) {
        ArrayList<String> usersByNumberRatings =
                new SortUser().sortUsersByNrOfRatings(sortType, users);

        while (usersByNumberRatings.size() > n) {
            usersByNumberRatings.remove(usersByNumberRatings.size() - 1);
        }

        return usersByNumberRatings;
    }
}
