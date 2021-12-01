package sort;

import actor.Actor;

import java.util.ArrayList;
import java.util.Comparator;

public final class SortActor {
    public ArrayList<String> sortActorsByAverageRating(final String sortType,
                                                       final ArrayList<Actor> actors) {
        ArrayList<Actor> actorsByRating = new ArrayList<>();
        ArrayList<String> actorsNamesByRating = new ArrayList<>();

        for (Actor actor: actors) {
            if (actor.getAverageRating() != 0) {
                actorsByRating.add(new Actor(actor));
            }
        }

        if (sortType.compareTo("asc") == 0) {
            actorsByRating.sort(Comparator.comparingDouble(Actor::getAverageRating).
                    thenComparing(Actor::getName));
        } else {
            actorsByRating.sort((a, b) -> {
                int result = Double.compare(b.getAverageRating(), a.getAverageRating());
                if (result != 0) {
                    return result;
                }

                return (-1) * a.getName().compareTo(b.getName());
            });
        }

        for (Actor actor: actorsByRating) {
            actorsNamesByRating.add(actor.getName());
        }

        return actorsNamesByRating;
    }

    public ArrayList<String> sortActorsByAwards(final String sortType,
                                                final ArrayList<Actor> actors) {
        ArrayList<String> sortedActorNames = new ArrayList<>();
        ArrayList<Actor> actorsByAwards = new ArrayList<>();

        for (Actor actor: actors) {
            actorsByAwards.add(new Actor(actor));
        }

        if (sortType.compareTo("asc") == 0) {
            actorsByAwards.sort((a, b) -> {
                int diff = a.getNrAwards() - b.getNrAwards();

                if (diff == 0) {
                    return a.getName().compareTo(b.getName());
                }

                return diff;
            });
        } else {
            actorsByAwards.sort((a, b) -> {
                int diff = b.getNrAwards() - a.getNrAwards();

                if (diff == 0) {
                    return (-1) * a.getName().compareTo(b.getName());
                }

                return diff;
            });
        }

        for (Actor actor: actorsByAwards) {
            sortedActorNames.add(actor.getName());
        }

        return sortedActorNames;
    }

    public ArrayList<String> sortActorsByName(final String sortType,
                                              final ArrayList<String> actors) {
        ArrayList<String> actorsByName = new ArrayList<>(actors);

        if (sortType.compareTo("asc") == 0) {
            actorsByName.sort(String::compareTo);
        } else {
            actorsByName.sort(Comparator.reverseOrder());
        }

        return actorsByName;
    }
}
