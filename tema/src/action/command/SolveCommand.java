package action.command;

import fileio.ActionInputData;
import result.Result;
import user.User;
import user.UserUtil;

public final class SolveCommand {
    public Result solveCommand(final ActionInputData command) {
        User user = new UserUtil().findUser(command.getUsername());
        if (user == null) {
            return new Result(command.getActionId(), "User doesn't exist");
        }
        String message = "";

        if (command.getType().compareTo("favorite") == 0) {
            message = new Command().favourite(user, command.getTitle());
        } else if (command.getType().compareTo("view") == 0) {
            message = new Command().view(user, command.getTitle());
        } else if (command.getType().compareTo("rating") == 0) {
            if (command.getSeasonNumber() == 0) {
                message =
                        new Command().addRatingMovie(user, command.getTitle(), command.getGrade());
            } else {
                message =
                        new Command().addRatingSerial(user, command.getTitle(),
                                command.getSeasonNumber(), command.getGrade());
            }
        }

        return new Result(command.getActionId(), message);
    }
}
