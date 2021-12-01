package sort;

import user.User;

import java.util.ArrayList;

public class SortUser {
    public ArrayList<String> sortUsersByNrOfRatings(final String sortType,
                                                    final ArrayList<User> users) {
        ArrayList<User> usersByNumberRatings = new ArrayList<>();
        ArrayList<String> sortedUsersNames = new ArrayList<>();

        for (User user: users) {
            if (user.getNrRatings() != 0) {
                usersByNumberRatings.add(new User(user));
            }
        }
        if (sortType.compareTo("asc") == 0) {
            usersByNumberRatings.sort((a, b) -> {
                int diff =  a.getNrRatings() - b.getNrRatings();

                if (diff == 0) {
                    return a.getUsername().compareTo(b.getUsername());
                }

                return diff;
            });
        } else {
            usersByNumberRatings.sort((a, b) -> {
                int diff = b.getNrRatings() - a.getNrRatings();

                if (diff == 0) {
                    return (-1) * a.getUsername().compareTo(b.getUsername());
                }

                return diff;
            });
        }

        for (User user: usersByNumberRatings) {
            sortedUsersNames.add(user.getUsername());
        }

        return sortedUsersNames;
    }
}
