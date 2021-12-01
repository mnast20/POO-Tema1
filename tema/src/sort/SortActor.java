package sort;

import actor.Actor;

import java.util.ArrayList;
import java.util.Comparator;

public final class SortActor {
    /**
     * Method returning the names of sorted actors by average rating, and then by names
     * in a given order
     */
    public ArrayList<String> sortActorsByAverageRating(final String sortType,
                                                       final ArrayList<Actor> actors) {
        ArrayList<Actor> actorsByRating = new ArrayList<>();
        ArrayList<String> actorsNamesByRating = new ArrayList<>();

        for (Actor actor: actors) {
            // add only actors with an existent average rating to the list
            if (actor.getAverageRating() != 0) {
                actorsByRating.add(new Actor(actor));
            }
        }

        if (sortType.compareTo("asc") == 0) {
            // sort actors by average rating and then by names in ascending order
            actorsByRating.sort(Comparator.comparingDouble(Actor::getAverageRating).
                    thenComparing(Actor::getName));
        } else {
            // sort actors by average rating and then by names in descending order
            actorsByRating.sort((a, b) -> {
                // compare actor ratings
                int result = Double.compare(b.getAverageRating(), a.getAverageRating());
                if (result != 0) {
                    return result;
                }

                // because actors have the same average rating
                // they are sorted in descending order based on their names
                return (-1) * a.getName().compareTo(b.getName());
            });
        }

        // get list of sorted actors' names
        for (Actor actor: actorsByRating) {
            actorsNamesByRating.add(actor.getName());
        }

        return actorsNamesByRating;
    }

    /**
     * Method returning the names of sorted actors by total numbers of awards, and then by names
     * in a given order
     */
    public ArrayList<String> sortActorsByAwards(final String sortType,
                                                final ArrayList<Actor> actors) {
        ArrayList<String> sortedActorNames = new ArrayList<>();
        ArrayList<Actor> actorsByAwards = new ArrayList<>();

        for (Actor actor: actors) {
            actorsByAwards.add(new Actor(actor));
        }

        if (sortType.compareTo("asc") == 0) {
            // sort actors by total number of awards and then by names in ascending order
            actorsByAwards.sort((a, b) -> {
                // calculate difference in number of awards
                int diff = a.getNrAwards() - b.getNrAwards();

                if (diff == 0) {
                    // because actors have the same number of awards
                    // they are sorted in ascending order based on their names
                    return a.getName().compareTo(b.getName());
                }

                return diff;
            });
        } else {
            // sort actors by total number of awards and then by names in descending order
            actorsByAwards.sort((a, b) -> {
                // calculate difference in number of awards
                int diff = b.getNrAwards() - a.getNrAwards();

                if (diff == 0) {
                    // because actors have the same number of awards
                    // they are sorted in descending order based on their names
                    return (-1) * a.getName().compareTo(b.getName());
                }

                return diff;
            });
        }

        // get list of sorted actors' names
        for (Actor actor: actorsByAwards) {
            sortedActorNames.add(actor.getName());
        }

        return sortedActorNames;
    }

    /**
     * Method returning the names of sorted actors by their names in given order
     */
    public ArrayList<String> sortActorsByName(final String sortType,
                                              final ArrayList<String> actors) {
        ArrayList<String> actorsByName = new ArrayList<>(actors);

        if (sortType.compareTo("asc") == 0) {
            // sort actors by name in ascending order
            actorsByName.sort(String::compareTo);
        } else {
            // sort actors by name in descending order
            actorsByName.sort(Comparator.reverseOrder());
        }

        return actorsByName;
    }
}
