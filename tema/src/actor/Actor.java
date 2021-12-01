package actor;

import entertainment.Video;
import entertainment.VideoUtil;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public final class Actor {
    private final String name;
    private final String careerDescription;
    private final ArrayList<String> filmography;
    private final Map<ActorsAwards, Integer> awards;
    private final int nrAwards;

    public Actor(final Actor actor) {
        this.name = actor.name;
        this.careerDescription = actor.careerDescription;
        this.filmography = new ArrayList<>();
        this.filmography.addAll(actor.filmography);
        this.awards = new HashMap<>();
        this.awards.putAll(actor.awards);
        this.nrAwards = actor.nrAwards;
    }

    public Actor(final ActorInputData actor) {
        name = actor.getName();
        careerDescription = actor.getCareerDescription();
        filmography = actor.getFilmography();
        awards = actor.getAwards();
        this.nrAwards = calculateNrAwards();
    }

    public String getName() {
        return name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public int getNrAwards() {
        return nrAwards;
    }

    public int calculateNrAwards() {
        int totalNrAwards = 0;
        for (ActorsAwards award: ActorsAwards.values()) {
            Integer nrAward = awards.get(award);
            if (nrAward != null) {
                totalNrAwards += nrAward;
            }
        }
        return totalNrAwards;
    }

    public boolean checkForKeywords(final ArrayList<String> keywords) {
        // search is case-insensitive
        String lowercaseText = careerDescription.toLowerCase();
        // word separators
        String separators = "'.,-)]\";!:?/\\ ";

        for (String keyword: keywords) {
            int len = 0;
            while (true) {
                int indexDescription = lowercaseText.substring(len).indexOf(keyword.toLowerCase());
                if (indexDescription == -1) {
                    return false;
                } else {
                    int characterIndex = len + indexDescription - 1;
                    len += indexDescription + keyword.length();

                    if (len >= lowercaseText.length()) {
                        return false;
                    }

                    if (characterIndex == -1
                            || separators.indexOf(lowercaseText.charAt(characterIndex)) != -1) {
                        if (separators.indexOf(lowercaseText.charAt(len)) != -1) {
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean checkAwards(final ArrayList<ActorsAwards> requiredAwards) {
        for (ActorsAwards award : requiredAwards) {
            if (awards.get(award) == null) {
                return false;
            }
        }
        return true;
    }

    public double getAverageRating() {
        double averageRating = 0;
        int nr = 0;

        for (String videoName: filmography) {
            Video video = new VideoUtil().findVideo(videoName);
            if (video != null) {
                double videoRating = video.getRating();
                if (videoRating != 0) {
                    averageRating += videoRating;
                    nr++;
                }
            }
        }
        if (nr != 0) {
            return averageRating / nr;
        }
        return 0;
    }
}
