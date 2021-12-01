package action.command;

import entertainment.Video;
import entertainment.Movie;
import entertainment.Show;
import entertainment.VideoUtil;
import user.User;

import java.util.ArrayList;
import java.util.Map;

public final class Command {
    public String favourite(final User user, final String videoName) {
        ArrayList<String> favourites = user.getFavouriteVideos();

        if (user.getHistory().get(videoName) == null) {
            return "error -> " + videoName + " is not seen";
        }

        for (String favourite: favourites) {
            if (favourite.compareTo(videoName) == 0) {
                return "error -> " + videoName + " is already in favourite list";
            }
        }

        Video video = new VideoUtil().findVideo(videoName);
        if (video != null) {
            favourites.add(videoName);
            video.setNrFavourites(video.getNrFavourites() + 1);

            return "success -> " + videoName + " was added as favourite";
        }

        return "error -> " + videoName + " doesn't exist in Database";
    }

    public String view(final User user, final String videoName) {
        Map<String, Integer> videoHistory = user.getHistory();
        Integer nrOfViews = videoHistory.get(videoName);
        if (nrOfViews == null) {
            nrOfViews = 0;
        }

        Video video = new VideoUtil().findVideo(videoName);
        if (video != null) {
            videoHistory.put(videoName, nrOfViews + 1);
            video.setNrViews(video.getNrViews() + 1);

            return "success -> " + videoName + " was viewed with total views of " + (nrOfViews + 1);
        }

        return "error -> " + videoName + " doesn't exist in Database";
    }

    public String addRatingMovie(final User user, final String videoName,
                                 final double givenRating) {
        if (user.getHistory().get(videoName) == null) {
            return "error -> " + videoName + " is not seen";
        }

        if (user.getRatedVideos().get(videoName) != null) {
            return "error -> " + videoName + " has been already rated";
        }

        Movie movie = (Movie) new VideoUtil().findVideo(videoName);
        if (movie != null) {
            user.getRatedVideos().put(videoName, new ArrayList<>());
            movie.getRatings().add(givenRating);
            movie.setRating(movie.calculateRating());
            user.setNrRatings(user.getNrRatings() + 1);

            return "success -> "
                    + videoName
                    + " was rated with "
                    + givenRating
                    + " by "
                    + user.getUsername();
        }

        return "error -> " + videoName + " doesn't exist in Database";
    }

    public String addRatingSerial(final User user, final String videoName,
                                  final Integer seasonNumber, final double givenRating) {
        if (user.getHistory().get(videoName) == null) {
            return "error -> " + videoName + " is not seen";
        }

        ArrayList<Integer> ratedSeasons = user.getRatedVideos().get(videoName);

        if (ratedSeasons != null) {
            for (Integer ratedSeason: ratedSeasons) {
                if (ratedSeason.equals(seasonNumber)) {
                    return "error -> " + videoName + " has been already rated";
                }
            }
        } else {
            ratedSeasons = new ArrayList<>();
        }

        Show show = (Show) new VideoUtil().findVideo(videoName);
        if (show != null) {
            ratedSeasons.add(seasonNumber);
            user.getRatedVideos().put(videoName, ratedSeasons);
            show.addRating(givenRating, seasonNumber);
            show.setRating(show.calculateRating());
            user.setNrRatings(user.getNrRatings() + 1);

            return "success -> "
                    + videoName
                    + " was rated with "
                    + givenRating
                    + " by "
                    + user.getUsername();
        }

        return "error -> " + videoName + " doesn't exist in Database";
    }
}
