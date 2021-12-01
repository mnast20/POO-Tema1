package action.command;

import entertainment.Video;
import entertainment.Movie;
import entertainment.Show;
import entertainment.VideoUtil;
import user.User;

import java.util.ArrayList;
import java.util.Map;

public final class Command {
    /**
     * Command for adding a movie/show to the user's list of favourites
     */
    public String favourite(final User user, final String videoName) {
        ArrayList<String> favourites = user.getFavouriteVideos();

        if (user.getHistory().get(videoName) == null) {
            // video wasn't found in user's history
            return "error -> " + videoName + " is not seen";
        }

        for (String favourite: favourites) {
            if (favourite.compareTo(videoName) == 0) {
                // video was previously added by user to the list of favourites
                return "error -> " + videoName + " is already in favourite list";
            }
        }

        // search video based on its title
        Video video = new VideoUtil().findVideo(videoName);
        if (video != null) {
            // add video to user's favourites list
            favourites.add(videoName);
            // increment the number of video's overall appearances in favourites lists
            video.setNrFavourites(video.getNrFavourites() + 1);

            return "success -> " + videoName + " was added as favourite";
        }

        // video doesn't exist in Database
        return "error -> " + videoName + " doesn't exist in Database";
    }

    /**
     * Command for watching a movie/show
     */
    public String view(final User user, final String videoName) {
        Map<String, Integer> videoHistory = user.getHistory();
        // get number of user's views for the given video
        Integer nrOfViews = videoHistory.get(videoName);
        if (nrOfViews == null) {
            // video has not been viewed by the user before
            nrOfViews = 0;
        }

        // search video based on its title
        Video video = new VideoUtil().findVideo(videoName);
        if (video != null) {
            // increment the number of movie/show views inside the history
            videoHistory.put(videoName, nrOfViews + 1);
            // increment the number of video's overall views
            video.setNrViews(video.getNrViews() + 1);

            return "success -> " + videoName + " was viewed with total views of " + (nrOfViews + 1);
        }

        // video doesn't exist in Database
        return "error -> " + videoName + " doesn't exist in Database";
    }

    /**
     * Command for giving a rating to a movie
     */
    public String addRatingMovie(final User user, final String videoName,
                                 final double givenRating) {
        if (user.getHistory().get(videoName) == null) {
            // movie wasn't found in user's history
            return "error -> " + videoName + " is not seen";
        }

        if (user.getRatedVideos().get(videoName) != null) {
            // movie was previously rated by user
            return "error -> " + videoName + " has been already rated";
        }

        // search movie based on its title
        Movie movie = (Movie) new VideoUtil().findVideo(videoName);
        if (movie != null) {
            // put the movie in the rated videos map with empty list because a movie has no seasons
            user.getRatedVideos().put(videoName, new ArrayList<>());
            // add the rating to the movie's rating list
            movie.getRatings().add(givenRating);
            // calculate and set movie's average rating
            movie.setRating(movie.calculateRating());
            // increment user's number of ratings
            user.setNrRatings(user.getNrRatings() + 1);

            return "success -> "
                    + videoName
                    + " was rated with "
                    + givenRating
                    + " by "
                    + user.getUsername();
        }

        // movie doesn't exist in Database
        return "error -> " + videoName + " doesn't exist in Database";
    }

    /**
     * Command for giving a rating to a show's season
     */
    public String addRatingSerial(final User user, final String videoName,
                                  final Integer seasonNumber, final double givenRating) {
        if (user.getHistory().get(videoName) == null) {
            // show wasn't found in user's history
            return "error -> " + videoName + " is not seen";
        }

        // get list of show's rated seasons (by user)
        ArrayList<Integer> ratedSeasons = user.getRatedVideos().get(videoName);

        if (ratedSeasons != null) {
            for (Integer ratedSeason: ratedSeasons) {
                if (ratedSeason.equals(seasonNumber)) {
                    // season was previously rated by user
                    return "error -> " + videoName + " has been already rated";
                }
            }
        } else {
            // user has never rated any season of the show
            ratedSeasons = new ArrayList<>();
        }

        Show show = (Show) new VideoUtil().findVideo(videoName);
        if (show != null) {
            // add the new season number to the list of show's rated seasons
            ratedSeasons.add(seasonNumber);
            // put the show in the rated videos map with its list of rated seasons
            user.getRatedVideos().put(videoName, ratedSeasons);
            // add the rating to the season's rating list
            show.addRating(givenRating, seasonNumber);
            // calculate and set show's average rating
            show.setRating(show.calculateRating());
            // increment user's number of ratings
            user.setNrRatings(user.getNrRatings() + 1);

            return "success -> "
                    + videoName
                    + " was rated with "
                    + givenRating
                    + " by "
                    + user.getUsername();
        }

        // show doesn't exist in Database
        return "error -> " + videoName + " doesn't exist in Database";
    }
}
