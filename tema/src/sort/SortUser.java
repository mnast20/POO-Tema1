package sort;

import user.User;

import java.util.ArrayList;

public class SortUser {
    /**
     * Method returning the usernames of sorted users by number of ratings, and then by usernames
     * in a given order
     */
    public ArrayList<String> sortUsersByNrOfRatings(final String sortType,
                                                    final ArrayList<User> users) {
        ArrayList<User> usersByNumberRatings = new ArrayList<>();
        ArrayList<String> sortedUsersNames = new ArrayList<>();

        for (User user: users) {
            if (user.getNrRatings() != 0) {
                // add to list only users that have previously given ratings
                usersByNumberRatings.add(new User(user));
            }
        }
        if (sortType.compareTo("asc") == 0) {
            // sort users by number of ratings and then by names in ascending order
            usersByNumberRatings.sort((a, b) -> {
                // calculate difference in number of ratings
                int diff =  a.getNrRatings() - b.getNrRatings();

                if (diff == 0) {
                    // because users have the same number of ratings
                    // they are sorted in ascending order based on their usernames
                    return a.getUsername().compareTo(b.getUsername());
                }

                return diff;
            });
        } else {
            // sort users by number of ratings and then by names in descending order
            usersByNumberRatings.sort((a, b) -> {
                // calculate difference in number of ratings
                int diff = b.getNrRatings() - a.getNrRatings();

                if (diff == 0) {
                    // because users have the same number of ratings
                    // they are sorted in descending order based on their usernames
                    return (-1) * a.getUsername().compareTo(b.getUsername());
                }

                return diff;
            });
        }

        // get list of sorted videos' titles
        for (User user: usersByNumberRatings) {
            sortedUsersNames.add(user.getUsername());
        }

        return sortedUsersNames;
    }
}
