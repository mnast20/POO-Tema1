package entertainment;

import database.Database;
import user.User;

import java.util.ArrayList;

public final class VideoUtil {
    /**
     * Method returning the titles of all the videos in given genre
     */
    public ArrayList<String> getVideoNamesInGenre(final String requiredGenre,
                                                  final ArrayList<Video> videoList) {
        ArrayList<String> videosInGenre = new ArrayList<>();

        for (Video video: videoList) {
            // check if the required genre is among the video's list of genres
            for (String genre: video.getGenres()) {
                if (requiredGenre.compareTo(genre) == 0) {
                    // add the video title to the list
                    videosInGenre.add(video.getTitle());
                    break;
                }
            }
        }

        return videosInGenre;
    }

    /**
     * Method searching for a video in the Database based on title
     */
    public Video findVideo(final String videoName) {
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();

        for (Video video: allVideos) {
            // check if video title exists in Database
            if (video.getTitle().compareTo(videoName) == 0) {
                return video;
            }
        }

        return null;
    }

    /**
     * Method returning all videos in the given genre
     */
    public ArrayList<Video> getVideosInGenre(final String requiredGenre,
                                             final ArrayList<Video> videoList) {
        ArrayList<Video> videosInGenre = new ArrayList<>();

        for (Video video: videoList) {
            for (String genre: video.getGenres()) {
                // check if the required genre is among the video's list of genres
                if (requiredGenre.compareTo(genre) == 0) {
                    // add the video to the list
                    videosInGenre.add(video);
                    break;
                }
            }
        }

        return videosInGenre;
    }

    /**
     * Method calculating all video views
     */
    public void calculateAllViews() {
        ArrayList<Video> videos = Database.getDatabase().getAllVideos();

        for (Video video: videos) {
            // get video views
            int nrViews = video.calculateVideoViews();
            // set video's number of views
            video.setNrViews(nrViews);
        }
    }

    /**
     * Method calculating all videos' number of favourites
     */
    public void calculateNrFavouritesVideos() {
        ArrayList<User> users = Database.getDatabase().getAllUsers();

        for (User user: users) {
            ArrayList<String> favourites = user.getFavouriteVideos();
            for (String favourite: favourites) {
                // find video from user's favourites
                Video video = new VideoUtil().findVideo(favourite);
                if (video != null) {
                    // increment video's number of favourites
                    video.setNrFavourites(video.getNrFavourites() + 1);
                }
            }
        }
    }
}
