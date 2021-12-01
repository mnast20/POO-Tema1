package user;

import database.Database;

import java.util.ArrayList;

public final class UserUtil {
    /**
     * Method searching for a user in Database based on username
     */
    public User findUser(final String userName) {
        ArrayList<User> allUsers = Database.getDatabase().getAllUsers();
        for (User user: allUsers) {
            // check if user has given username
            if (user.getUsername().compareTo(userName) == 0) {
                return user;
            }
        }
        return null;
    }
}
