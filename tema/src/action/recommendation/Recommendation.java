package action.recommendation;

import database.Database;
import entertainment.Video;
import entertainment.VideoUtil;
import sort.SortVideo;
import user.User;

import java.util.ArrayList;
import java.util.Map;

public final class Recommendation {
    public String standard(final User user) {
        ArrayList<Video> videos = Database.getDatabase().getAllVideos();
        Map<String, Integer> history = user.getHistory();

        for (Video video: videos) {
            if (history.get(video.getTitle()) == null) {
                return video.getTitle();
            }
        }

        return null;
    }

    public String bestUnseen(final User user) {
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();
        ArrayList<String> videosByRatings =
                new SortVideo().sortVideosByRating("desc", allVideos, 0, 1);
        Map<String, Integer> history = user.getHistory();

        for (String videoName: videosByRatings) {
            if (history.get(videoName) == null) {
                return videoName;
            }
        }

        return null;
    }

    public String popular(final User user) {
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();
        ArrayList<String> genresByViews = new SortVideo().sortGenreViews("desc");

        for (String genre: genresByViews) {
            ArrayList<String> videosInGenre =
                    new VideoUtil().getVideoNamesInGenre(genre, allVideos);
            for (String videoName: videosInGenre) {
                if (user.getHistory().get(videoName) == null) {
                    return videoName;
                }
            }
        }

        return null;
    }

    public String favorite(final User user) {
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();
        ArrayList<String> videosByFavourites =
                new SortVideo().sortVideosByFavourite("desc", allVideos, 0);

        for (String video: videosByFavourites) {
            if (user.getHistory().get(video) == null) {
                return video;
            }
        }

        return null;
    }

    public ArrayList<String> search(final User user, final String genre) {
        ArrayList<Video> unseen = new ArrayList<>();
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();
        ArrayList<Video> videosInGenre = new VideoUtil().getVideosInGenre(genre, allVideos);

        for (Video video: videosInGenre) {
            if (user.getHistory().get(video.getTitle()) == null) {
                unseen.add(new Video(video));
            }
        }

        if (unseen.size() == 0) {
            return null;
        }

        return new SortVideo().sortVideosByRating("asc", unseen, 1, 1);
    }
}
