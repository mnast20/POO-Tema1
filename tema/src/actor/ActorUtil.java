package actor;

import java.util.ArrayList;

public final class ActorUtil {
    /**
     * Method returning all actors with required awards
     */
    public ArrayList<Actor> getActorsWithAwards(final ArrayList<ActorsAwards> requiredAwards,
                                                final ArrayList<Actor> actors) {
        ArrayList<Actor> actorsWithAwards = new ArrayList<>();
        for (Actor actor: actors) {
            // check if actor has all required awards
            if (actor.checkAwards(requiredAwards)) {
                // add actor to list
                actorsWithAwards.add(actor);
            }
        }
        return actorsWithAwards;
    }

    /**
     * Method returning the names of all actors with required awards in their description
     */
    public ArrayList<String> getActorsWithKeywords(final ArrayList<String> keywords,
                                                   final ArrayList<Actor> actors) {
        ArrayList<String> actorsWithKeywords = new ArrayList<>();
        for (Actor actor: actors) {
            // check if actor has all required words in their career description
            if (actor.checkForKeywords(keywords)) {
                // add actor to list
                actorsWithKeywords.add(actor.getName());
            }
        }
        return actorsWithKeywords;
    }

    /**
     * Method returning the names of all actors from a given list
     */
    public ArrayList<String> getActorNames(final ArrayList<Actor> actors) {
        ArrayList<String> actorNames = new ArrayList<>();
        for (Actor actor: actors) {
            // add actor's name to the list
            actorNames.add(actor.getName());

        }
        return actorNames;
    }
}
