package sort;

import database.Database;
import entertainment.Video;


import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

public final class SortVideo {
    public ArrayList<String> sortVideosByRating(final String sortType,
                                                final ArrayList<Video> videos,
                                                final int sortByName, final int recommendation) {
        ArrayList<Video> videosByRating = new ArrayList<>();
        ArrayList<String> sortedVideoNames = new ArrayList<>();

        for (Video video: videos) {
            if (video.getRating() != 0 && recommendation == 0) {
                videosByRating.add(video.copy());
            } else if (recommendation == 1) {
                videosByRating.add(video.copy());
            }
        }

        if (sortType.compareTo("asc") == 0) {
            videosByRating.sort((a, b) -> {
                int result = Double.compare(a.getRating(), b.getRating());

                if (result == 0 && sortByName == 1) {
                    return a.getTitle().compareTo(b.getTitle());
                }

                return result;
            });
        } else {
            videosByRating.sort((a, b) -> {
                int result = Double.compare(b.getRating(), a.getRating());

                if (result == 0 && sortByName == 1) {
                    return (-1) * a.getTitle().compareTo(b.getTitle());
                }

                return result;
            });
        }

        for (Video video: videosByRating) {
            sortedVideoNames.add(video.getTitle());
        }

        return sortedVideoNames;
    }

    public ArrayList<String> sortGenreViews(final String sortType) {
        Map<String, Integer> genresViews = new HashMap<>();
        ArrayList<String> genresByViews = new ArrayList<>();
        ArrayList<Video> allVideos = Database.getDatabase().getAllVideos();

        for (Video video: allVideos) {
            int views = video.getNrViews();

            for (String genre : video.getGenres()) {
                Integer genreViews = genresViews.get(genre);

                if (genreViews == null) {
                    genresByViews.add(genre);
                    genreViews = 0;
                }
                genresViews.put(genre, genreViews + views);
            }
        }

        if (sortType.compareTo("asc") == 0) {
            genresByViews.sort(Comparator.comparingInt(genresViews::get));
        } else {
            genresByViews.sort((a, b) -> genresViews.get(b) - genresViews.get(a));
        }

        return genresByViews;
    }

    public ArrayList<String> sortVideosByFavourite(final String sortType,
                                                   final ArrayList<Video> videos,
                                                   final int sortByName) {
        ArrayList<Video> videosByNrFavourites = new ArrayList<>();
        ArrayList<String> videoNames = new ArrayList<>();

        for (Video video: videos) {
            if (video.getNrFavourites() != 0) {
                videosByNrFavourites.add(video.copy());
            }
        }

        if (sortType.compareTo("asc") == 0) {
            videosByNrFavourites.sort((a, b) -> {
                int diff = a.getNrFavourites() - b.getNrFavourites();

                if (diff == 0 && sortByName == 1) {
                    return a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        } else {
            videosByNrFavourites.sort((a, b) -> {
                int diff = b.getNrFavourites() - a.getNrFavourites();

                if (diff == 0 && sortByName == 1) {
                    return (-1) * a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        }

        for (Video video: videosByNrFavourites) {
            videoNames.add(video.getTitle());
        }

        return videoNames;
    }

    public ArrayList<String> sortVideosByLength(final String sortType,
                                                final ArrayList<Video> videos) {
        ArrayList<Video> videosByLength = new ArrayList<>();
        ArrayList<String> namesVideosByLength = new ArrayList<>();

        for (Video video: videos) {
            videosByLength.add(video.copy());
        }

        if (sortType.compareTo("asc") == 0) {
            videosByLength.sort((a, b) -> {
                int diff = a.getDuration() - b.getDuration();

                if (diff == 0) {
                    return a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        } else {
            videosByLength.sort((a, b) -> {
                int diff = b.getDuration() - a.getDuration();

                if (diff == 0) {
                    return (-1) * a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        }

        for (Video video: videosByLength) {
            namesVideosByLength.add(video.getTitle());
        }

        return namesVideosByLength;
    }

    public ArrayList<String> sortVideosByViews(final String sortType,
                                               final ArrayList<Video> videos) {
        ArrayList<Video> videosByViews = new ArrayList<>();
        ArrayList<String> videoNames = new ArrayList<>();

        for (Video video: videos) {
            if (video.getNrViews() != 0) {
                videosByViews.add(video.copy());
            }
        }
        if (sortType.compareTo("asc") == 0) {
            videosByViews.sort((a, b) -> {
                int diff = a.getNrViews() - b.getNrViews();

                if (diff == 0) {
                    return a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        } else {
            videosByViews.sort((a, b) -> {
                int diff = b.getNrViews() - a.getNrViews();

                if (diff == 0) {
                    return (-1) * a.getTitle().compareTo(b.getTitle());
                }

                return diff;
            });
        }

        for (Video video: videosByViews) {
            videoNames.add(video.getTitle());
        }

        return videoNames;
    }

}
