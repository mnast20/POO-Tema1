package action.query;

import actor.Actor;
import actor.ActorsAwards;
import entertainment.Video;

import java.util.ArrayList;

public final class Filter {
    /**
     * Method returning all videos made in given years from a list of videos
     */
    public ArrayList<Video> yearFilter(final ArrayList<String> years,
                                       final ArrayList<Video> videos) {
        ArrayList<Video> videosMadeInYears = new ArrayList<>();

        for (Video video: videos) {
            for (String yearString: years) {
                // turn the year String into an Integer
                int year = Integer.parseInt(yearString);
                // check if video was made in given year
                if (video.getYear() == year) {
                    // add video to filtered list
                    videosMadeInYears.add(video);
                }
            }
        }

        return videosMadeInYears;
    }

    /**
     * Method returning all videos with given genres from a list of videos
     */
    public ArrayList<Video> genreFilter(final ArrayList<String> genres,
                                        final ArrayList<Video> videoList) {
        ArrayList<Video> videosMadeInGenres = new ArrayList<>();

        for (Video video: videoList) {
            // check if video has all the given genres
            if (video.checkVideoGenres(genres)) {
                // add video to filtered list
                videosMadeInGenres.add(video);
            }
        }

        return videosMadeInGenres;
    }

    /**
     * Method returning all actors with given words in their career description from an actor list
     */
    public ArrayList<Actor> wordsFilter(final ArrayList<String> words,
                                        final ArrayList<Actor> actors) {
        ArrayList<Actor> actorsWithKeywords = new ArrayList<>();

        for (Actor actor: actors) {
            // check if actor has all the given awards
            if (actor.checkForKeywords(words)) {
                // add actor to filtered list
                actorsWithKeywords.add(actor);
            }
        }

        return actorsWithKeywords;
    }

    /**
     * Method returning all actors with given awards from an actor list
     */
    public ArrayList<Actor> awardsFilter(final ArrayList<ActorsAwards> awards,
                                         final ArrayList<Actor> actors) {
        ArrayList<Actor> actorsWithAwards = new ArrayList<>();

        for (Actor actor: actors) {
            // check if actor's career description includes all given words
            if (actor.checkAwards(awards)) {
                // add actor to filtered list
                actorsWithAwards.add(actor);
            }
        }

        return actorsWithAwards;
    }
}
