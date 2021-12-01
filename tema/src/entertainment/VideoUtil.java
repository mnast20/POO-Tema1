package entertainment;

import database.Database;
import user.User;

import java.util.ArrayList;

public final class VideoUtil {
    public ArrayList<String> getVideoNamesInGenre(final String requiredGenre,
                                                  final ArrayList<Video> videoList) {
        ArrayList<String> videosInGenre = new ArrayList<>();

        for (Video video: videoList) {
            for (String genre: video.getGenres()) {
                if (requiredGenre.compareTo(genre) == 0) {
                    videosInGenre.add(video.getTitle());
                    break;
                }
            }
        }

        return videosInGenre;
    }

    public Video findVideo(final String videoName) {
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();

        for (Video video: allVideos) {
            if (video.getTitle().compareTo(videoName) == 0) {
                return video;
            }
        }

        return null;
    }

    public ArrayList<Video> getVideosInGenre(final String requiredGenre,
                                             final ArrayList<Video> videoList) {
        ArrayList<Video> videosInGenre = new ArrayList<>();

        for (Video video: videoList) {
            for (String genre: video.getGenres()) {
                if (requiredGenre.compareTo(genre) == 0) {
                    videosInGenre.add(video);
                    break;
                }
            }
        }

        return videosInGenre;
    }

    public void calculateAllViews() {
        ArrayList<Video> videos = Database.getDatabase().getAllVideos();

        for (Video video: videos) {
            int nrViews = video.calculateVideoViews();
            video.setNrViews(nrViews);
        }
    }

    public void calculateNrFavouritesVideos() {
        ArrayList<User> users = Database.getDatabase().getAllUsers();

        for (User user: users) {
            ArrayList<String> favourites = user.getFavouriteVideos();
            for (String favourite: favourites) {
                Video video = new VideoUtil().findVideo(favourite);
                if (video != null) {
                    video.setNrFavourites(video.getNrFavourites() + 1);
                }
            }
        }
    }
}
