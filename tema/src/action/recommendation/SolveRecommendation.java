package action.recommendation;

import fileio.ActionInputData;
import result.Result;
import user.User;
import user.UserUtil;

import java.util.ArrayList;

public final class SolveRecommendation {
    /**
     * Method for solving any type of Recommendation
     */
    public Result solveRecommendation(final ActionInputData recommendation) {
        // find user requesting recommendations
        User user = new UserUtil().findUser(recommendation.getUsername());
        if (user == null) {
            return new Result(recommendation.getActionId(), "User doesn't exist");
        }

        String recommendationType = recommendation.getType();
        String message = recommendationType.substring(0, 1).toUpperCase()
                        + recommendationType.substring(1)
                        + "Recommendation";

        if (recommendationType.compareTo("standard") == 0) {
            String result = new Recommendation().standard(user);

            // get Standard recommendation message
            if (result == null) {
                message += " cannot be applied!";
            } else {
                message +=  " result: " + result;
            }
        } else if (recommendationType.compareTo("best_unseen") == 0) {
            // get Best Rated Unseen recommendation message
            message = "BestRatedUnseenRecommendation";
            String result = new Recommendation().bestUnseen(user);

            if (result == null) {
                message += " cannot be applied!";
            } else {
                message +=  " result: " + result;
            }
        } else if (user.getSubscriptionType().compareTo("PREMIUM") == 0) {
            // only for users with Premium subscription
            if (recommendationType.compareTo("popular") == 0) {
                // get Popular recommendation message
                String result = new Recommendation().popular(user);

                if (result == null) {
                    message += " cannot be applied!";
                } else {
                    message +=  " result: " + result;
                }
            } else if (recommendationType.compareTo("favorite") == 0) {
                // get Favorite recommendation message
                String result = new Recommendation().favorite(user);

                if (result == null) {
                    message += " cannot be applied!";
                } else {
                    message +=  " result: " + result;
                }
            } else if (recommendationType.compareTo("search") == 0) {
                // get Search recommendation message
                ArrayList<String> result =
                        new Recommendation().search(user, recommendation.getGenre());

                if (result == null) {
                    message += " cannot be applied!";
                } else {
                    message += " result: " + result;
                }
            }
        } else {
            // user's subscription doesn't allow Premium recommendations
            message += " cannot be applied!";
        }

        return new Result(recommendation.getActionId(), message);
    }
}
