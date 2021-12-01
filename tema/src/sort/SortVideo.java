package sort;

import database.Database;
import entertainment.Video;


import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

public final class SortVideo {
    /**
     * Method returning the titles of sorted videos by average rating,
     * and then, if needed, by their titles in a given order
     */
    public ArrayList<String> sortVideosByRating(final String sortType,
                                                final ArrayList<Video> videos,
                                                final int sortByName, final int recommendation) {
        ArrayList<Video> videosByRating = new ArrayList<>();
        ArrayList<String> sortedVideoNames = new ArrayList<>();

        for (Video video: videos) {
            if (video.getRating() != 0 && recommendation == 0) {
                // if the sort function is called by a query,
                // add all titles of videos with a rating to the list
                videosByRating.add(video.copy());
            } else if (recommendation == 1) {
                // if the sort function is called by a recommendation,
                // add all titles of the videos
                videosByRating.add(video.copy());
            }
        }

        if (sortType.compareTo("asc") == 0) {
            // sort videos by average rating and then, if needed, by titles in ascending order
            videosByRating.sort((a, b) -> {
                // compare video ratings
                int result = Double.compare(a.getRating(), b.getRating());

                if (result == 0 && sortByName == 1) {
                    // videos are required to be sorted by title in ascending order
                    return a.getTitle().compareTo(b.getTitle());
                }

                return result;
            });
        } else {
            // sort videos by average rating and then, if needed, by titles in descending order
            videosByRating.sort((a, b) -> {
                int result = Double.compare(b.getRating(), a.getRating());

                if (result == 0 && sortByName == 1) {
                    // videos are required to be sorted by title in descending order
                    return (-1) * a.getTitle().compareTo(b.getTitle());
                }

                return result;
            });
        }

        // get list of videos' titles
        for (Video video: videosByRating) {
            sortedVideoNames.add(video.getTitle());
        }

        return sortedVideoNames;
    }

    /**
     * Method sorting genres by views in a given order
     */
    public ArrayList<String> sortGenreViews(final String sortType) {
        // map genres and their views
        Map<String, Integer> genresViews = new HashMap<>();
        ArrayList<String> genresByViews = new ArrayList<>();
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();

        for (Video video: allVideos) {
            // get video views
            int views = video.getNrViews();

            // add video's genres' views to map
            for (String genre : video.getGenres()) {
                Integer genreViews = genresViews.get(genre);

                // genre wasn't put in map before
                if (genreViews == null) {
                    genresByViews.add(genre);
                    genreViews = 0;
                }
                // put genre and its views in map
                genresViews.put(genre, genreViews + views);
            }
        }

        if (sortType.compareTo("asc") == 0) {
            // sort genres by views in ascending order
            genresByViews.sort(Comparator.comparingInt(genresViews::get));
        } else {
            // sort genres by views in descending order
            genresByViews.sort((a, b) -> genresViews.get(b) - genresViews.get(a));
        }

        return genresByViews;
    }

    /**
     * Method returning the titles of sorted videos by number of favourites,
     * and then, if needed, by their titles in a given order
     */
    public ArrayList<String> sortVideosByFavourite(final String sortType,
                                                   final ArrayList<Video> videos,
                                                   final int sortByName) {
        ArrayList<Video> videosByNrFavourites = new ArrayList<>();
        ArrayList<String> videoNames = new ArrayList<>();

        for (Video video: videos) {
            if (video.getNrFavourites() != 0) {
                // add only the videos that are present in users' favourites to the list
                videosByNrFavourites.add(video.copy());
            }
        }

        if (sortType.compareTo("asc") == 0) {
            // sort videos by number of favourites and then, if needed, by titles in ascending order
            videosByNrFavourites.sort((a, b) -> {
                // calculate difference in number of favourites
                int diff = a.getNrFavourites() - b.getNrFavourites();

                if (diff == 0 && sortByName == 1) {
                    // videos are required to be sorted by title in ascending order
                    return a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        } else {
            // sort videos by number of favourites and then, if needed,
            // by titles in descending order
            videosByNrFavourites.sort((a, b) -> {
                // calculate difference in number of favourites
                int diff = b.getNrFavourites() - a.getNrFavourites();

                if (diff == 0 && sortByName == 1) {
                    // videos are required to be sorted by title in descending order
                    return (-1) * a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        }

        // get list of videos' titles
        for (Video video: videosByNrFavourites) {
            videoNames.add(video.getTitle());
        }

        return videoNames;
    }

    /**
     * Method returning the titles of sorted videos by length, and then by their titles
     * in a given order
     */
    public ArrayList<String> sortVideosByLength(final String sortType,
                                                final ArrayList<Video> videos) {
        ArrayList<Video> videosByLength = new ArrayList<>();
        ArrayList<String> namesVideosByLength = new ArrayList<>();

        for (Video video: videos) {
            // add copy of video to list
            videosByLength.add(video.copy());
        }

        if (sortType.compareTo("asc") == 0) {
            // sort videos by length and then by titles in ascending order
            videosByLength.sort((a, b) -> {
                // calculate difference in length
                int diff = a.getDuration() - b.getDuration();

                if (diff == 0) {
                    // because videos have the same length
                    // they are sorted in ascending order based on their titles
                    return a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        } else {
            // sort videos by length and then by titles in descending order
            videosByLength.sort((a, b) -> {
                // calculate difference in length
                int diff = b.getDuration() - a.getDuration();

                if (diff == 0) {
                    // because videos have the same length
                    // they are sorted in descending order based on their titles
                    return (-1) * a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        }

        // get list of videos' titles
        for (Video video: videosByLength) {
            namesVideosByLength.add(video.getTitle());
        }

        return namesVideosByLength;
    }

    /**
     * Method returning the titles of sorted videos by views, and then by their titles
     * in a given order
     */
    public ArrayList<String> sortVideosByViews(final String sortType,
                                               final ArrayList<Video> videos) {
        ArrayList<Video> videosByViews = new ArrayList<>();
        ArrayList<String> videoNames = new ArrayList<>();

        for (Video video: videos) {
            // add only copies of viewed videos to list
            if (video.getNrViews() != 0) {
                videosByViews.add(video.copy());
            }
        }
        if (sortType.compareTo("asc") == 0) {
            // sort videos by views and then by titles in ascending order
            videosByViews.sort((a, b) -> {
                // calculate difference in number of views
                int diff = a.getNrViews() - b.getNrViews();

                if (diff == 0) {
                    // because videos have the same number of views
                    // they are sorted in ascending order based on their titles
                    return a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        } else {
            // sort videos by views and then by titles in descending order
            videosByViews.sort((a, b) -> {
                // calculate difference in number of views
                int diff = b.getNrViews() - a.getNrViews();

                if (diff == 0) {
                    // because videos have the same number of views
                    // they are sorted in descending order based on their titles
                    return (-1) * a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        }

        // get list of videos' titles
        for (Video video: videosByViews) {
            videoNames.add(video.getTitle());
        }

        return videoNames;
    }

}
