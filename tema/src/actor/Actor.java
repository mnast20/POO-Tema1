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

    /**
     * Actor copy constructor based on another actor
     */
    public Actor(final Actor actor) {
        this.name = actor.name;
        this.careerDescription = actor.careerDescription;
        this.filmography = new ArrayList<>();
        this.filmography.addAll(actor.filmography);
        this.awards = new HashMap<>();
        this.awards.putAll(actor.awards);
        this.nrAwards = actor.nrAwards;
    }

    /**
     * Actor copy constructor based on actor's input data
     */
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

    /**
     * Method calculating actor's number of awards
     */
    public int calculateNrAwards() {
        int totalNrAwards = 0;
        for (ActorsAwards award: ActorsAwards.values()) {
            // get number of times award was given to the actor
            Integer nrAward = awards.get(award);
            if (nrAward != null) {
                totalNrAwards += nrAward;
            }
        }
        return totalNrAwards;
    }

    /**
     * Method checking if actor's career description includes all required keywords
     */
    public boolean checkForKeywords(final ArrayList<String> keywords) {
        // search is case-insensitive
        String lowercaseText = careerDescription.toLowerCase();
        // word separators
        String separators = "'.,-)]\";!:?/\\ ";

        for (String keyword: keywords) {
            int len = 0; // index of text substring
            while (true) {
                // check if keyword is present in text substring
                int indexDescription = lowercaseText.substring(len).indexOf(keyword.toLowerCase());
                if (indexDescription == -1) {
                    // keyword was not found
                    return false;
                } else {
                    // get character preceding the keyword inside the text
                    int characterIndex = len + indexDescription - 1;
                    // get the index of character succeeding the keyword
                    len += indexDescription + keyword.length();

                    // the index of future substring exceeds text's length
                    if (len >= lowercaseText.length()) {
                        // keyword is only a pattern in text, but not an actual word
                        return false;
                    }

                    // keyword is preceded by a text separator/is present at the start of the text
                    if (characterIndex == -1
                            || separators.indexOf(lowercaseText.charAt(characterIndex)) != -1) {
                        // check if the found keyword is succeeded by a separator inside the text
                        if (separators.indexOf(lowercaseText.charAt(len)) != -1) {
                            // found keyword as actual word in text
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Method checking if actor has all required awards
     */
    public boolean checkAwards(final ArrayList<ActorsAwards> requiredAwards) {
        for (ActorsAwards award : requiredAwards) {
            // check if actor has wanted award
            if (awards.get(award) == null) {
                // award was not given to actor
                return false;
            }
        }
        return true;
    }

    /**
     * Method calculating actor's average rating
     */
    public double getAverageRating() {
        double averageRating = 0;
        int nr = 0;

        for (String videoName: filmography) {
            Video video = new VideoUtil().findVideo(videoName);
            if (video != null) {
                // get video rating and check if it was previously rated
                double videoRating = video.getRating();
                if (videoRating != 0) {
                    // get sum of actor's video ratings
                    averageRating += videoRating;
                    nr++;
                }
            }
        }
        if (nr != 0) {
            // value of average rating
            return averageRating / nr;
        }
        return 0;
    }
}
