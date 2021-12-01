package user;

import database.Database;

import java.util.ArrayList;

public final class UserUtil {
    public User findUser(final String userName) {
        ArrayList<User> allUsers = Database.getDatabase().getAllUsers();
        for (User user: allUsers) {
            if (user.getUsername().compareTo(userName) == 0) {
                return user;
            }
        }
        return null;
    }
}
