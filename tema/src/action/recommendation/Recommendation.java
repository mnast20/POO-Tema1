package action.recommendation;

import database.Database;
import entertainment.Video;
import entertainment.VideoUtil;
import sort.SortVideo;
import user.User;

import java.util.ArrayList;
import java.util.Map;

public final class Recommendation {
    /**
     * Recommendation returning title of the first video in Database that is not seen by user
     */
    public String standard(final User user) {
        ArrayList<Video> videos = Database.getDatabase().getAllVideos();
        Map<String, Integer> history = user.getHistory();

        for (Video video: videos) {
            // check if video is in user's history
            if (history.get(video.getTitle()) == null) {
                return video.getTitle();
            }
        }

        return null;
    }

    /**
     * Recommendation returning title of the best rated video that is not seen by user
     */
    public String bestUnseen(final User user) {
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();
        // get video titles from sorted list of videos by rating in descending order
        ArrayList<String> videosByRatings =
                new SortVideo().sortVideosByRating("desc", allVideos, 0, 1);
        Map<String, Integer> history = user.getHistory();

        for (String videoName: videosByRatings) {
            // check if video is in user's history
            if (history.get(videoName) == null) {
                return videoName;
            }
        }

        return null;
    }

    /**
     * Recommendation returning title of the video in the most popular genre that
     * is not seen by user
     */
    public String popular(final User user) {
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();
        // get sorted genres by number of views
        ArrayList<String> genresByViews = new SortVideo().sortGenreViews("desc");

        for (String genre: genresByViews) {
            // get all videos in genre
            ArrayList<String> videosInGenre =
                    new VideoUtil().getVideoNamesInGenre(genre, allVideos);
            for (String videoName: videosInGenre) {
                // check if video is in user's history
                if (user.getHistory().get(videoName) == null) {
                    return videoName;
                }
            }
        }

        return null;
    }

    /**
     * Recommendation returning title of the video that appears most often in favourite lists
     * and isn't seen by user
     */
    public String favorite(final User user) {
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();
        // get video titles from sorted list of videos by number of favourites
        ArrayList<String> videosByFavourites =
                new SortVideo().sortVideosByFavourite("desc", allVideos, 0);

        for (String video: videosByFavourites) {
            // check if video is in user's history
            if (user.getHistory().get(video) == null) {
                return video;
            }
        }

        return null;
    }

    /**
     * Recommendation returning list of videos in a genre, unseen by user that are sorted by rating
     * in ascending order
     */
    public ArrayList<String> search(final User user, final String genre) {
        ArrayList<Video> unseen = new ArrayList<>();
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();
        // get all videos in genre
        ArrayList<Video> videosInGenre = new VideoUtil().getVideosInGenre(genre, allVideos);

        for (Video video: videosInGenre) {
            // check if video is in user's history
            if (user.getHistory().get(video.getTitle()) == null) {
                // add video to list
                unseen.add(new Video(video));
            }
        }

        if (unseen.size() == 0) {
            // user has no unseen videos in specified genre
            return null;
        }

        // get video titles from sorted unseen videos in ascending order by rating
        return new SortVideo().sortVideosByRating("asc", unseen, 1, 1);
    }
}
