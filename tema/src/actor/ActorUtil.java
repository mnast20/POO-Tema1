package actor;

import java.util.ArrayList;

public final class ActorUtil {
    public ArrayList<Actor> getActorsWithAwards(final ArrayList<ActorsAwards> requiredAwards,
                                                final ArrayList<Actor> actors) {
        ArrayList<Actor> actorsWithAwards = new ArrayList<>();
        for (Actor actor: actors) {
            if (actor.checkAwards(requiredAwards)) {
                actorsWithAwards.add(actor);
            }
        }
        return actorsWithAwards;
    }

    public ArrayList<String> getActorsWithKeywords(final ArrayList<String> keywords,
                                                   final ArrayList<Actor> actors) {
        ArrayList<String> actorsWithKeywords = new ArrayList<>();
        for (Actor actor: actors) {
            if (actor.checkForKeywords(keywords)) {
                actorsWithKeywords.add(actor.getName());
            }
        }
        return actorsWithKeywords;
    }

    public ArrayList<String> getActorNames(final ArrayList<Actor> actors) {
        ArrayList<String> actorNames = new ArrayList<>();
        for (Actor actor: actors) {
            actorNames.add(actor.getName());

        }
        return actorNames;
    }
}
