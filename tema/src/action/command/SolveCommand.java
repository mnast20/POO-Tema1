package action.command;

import fileio.ActionInputData;
import result.Result;
import user.User;
import user.UserUtil;

public final class SolveCommand {
    /**
     * Method for solving any type of User Command
     */
    public Result solveCommand(final ActionInputData command) {
        // find user giving command
        User user = new UserUtil().findUser(command.getUsername());
        if (user == null) {
            // user wasn't found
            return new Result(command.getActionId(), "User doesn't exist");
        }
        String message = "";

        if (command.getType().compareTo("favorite") == 0) {
            // get the Favourite command message
            message = new Command().favourite(user, command.getTitle());
        } else if (command.getType().compareTo("view") == 0) {
            // get the View command message
            message = new Command().view(user, command.getTitle());
        } else if (command.getType().compareTo("rating") == 0) {
            // get the Add Rating command message
            if (command.getSeasonNumber() == 0) {
                // the command specifies the season number and gives a rating to a show's season
                message =
                        new Command().addRatingMovie(user, command.getTitle(), command.getGrade());
            } else {
                // the command gives a rating to a movie
                message =
                        new Command().addRatingSerial(user, command.getTitle(),
                                command.getSeasonNumber(), command.getGrade());
            }
        }

        // put the message and the action's id in the result
        return new Result(command.getActionId(), message);
    }
}
