package action;

import action.command.SolveCommand;
import action.query.SolveQuery;
import action.recommendation.SolveRecommendation;
import fileio.ActionInputData;
import result.Result;


public final class SolveAction {
    private Result finalResult;

    public Result getFinalResult() {
        return finalResult;
    }

    public void solveAction(final ActionInputData action) {
        if (action.getActionType().compareTo("command") == 0) {
            finalResult = new SolveCommand().solveCommand(action);
        } else if (action.getActionType().compareTo("query") == 0) {
            finalResult = new SolveQuery().solveQuery(action);
        } else if (action.getActionType().compareTo("recommendation") == 0) {
            finalResult = new SolveRecommendation().solveRecommendation(action);
        }
    }




}
