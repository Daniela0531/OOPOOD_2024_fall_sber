package ru.mipt.bit.platformer.game_management.execution;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.Map;
import ru.mipt.bit.platformer.game_management.CommandQueueHandler;
import ru.mipt.bit.platformer.game_management.actions.Action;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level_map.MapNode;

import java.util.HashMap;

import static com.badlogic.gdx.math.MathUtils.isEqual;


@Component
public class MovementsExecutor {
    private int upBound = 5;
    private int leftBound = 0;
    private int lowBound = 0;
    private int rightBound = 9;

    public MovementsExecutor() {
//        this.movementCommands = new ArrayList<>();
//        this.map = map;
    }

    public boolean movementIsPossible(GridPoint2 destCoordinates, Map map) {
        GridPoint2 newCoordinates = destCoordinates.cpy();
        if (newCoordinates.x < leftBound || newCoordinates.x > rightBound || newCoordinates.y < lowBound || newCoordinates.y > upBound) {
            return false;
        }
        for(MapNode obstacleCoordinates : map.getNodes()) {
            if (obstacleCoordinates.getCoordinates().equals(newCoordinates)) {
                return false;
            }
        }
        return true;
    }

    public void tryToCatchNewCommand(CommandQueueHandler commandQueueHandler, Level level) {
//        commandQueueHandler.printQueue();
        if (commandQueueHandler.isEmpty()) {
            return;
        }
//        System.out.println("new commands:");
        for (Action action : commandQueueHandler.getActions()) {
//            System.out.println("is move:" + moveAction.getModel().isMoving());
            if (!action.getModel().isMoving()) {
                action.getModel().setDirection(action.getDirection());
                action.getModel().setMovingStatus(true);
//                System.out.println("    " + moveAction.getActionType() +
//                        ": " + moveAction.getModel().getCoordinates() +
//                        "  " + moveAction.getDirection().getVector());
            }
        }
        commandQueueHandler.clear();
//        System.out.println("clear, size: " + commandQueueHandler.size());
    }

    public void finishActionIfPossible(Action action) {
        if (isEqual(action.getModel().getProgress(), 1f)) {
            action.getModel().finishMovement();
            action.getModel().setProgress(0f);
            action.getModel().setMovingStatus(false);
            action.finished();
//            executingActionsQueue.remove(tankMoveModel);
//            return true;
        }
//        return false;
    }

    public void executeMovement(float deltaTime, Action action, Map map) {
        if (action.getModel().isMoving()) {
            if (movementIsPossible(action.getModel().getDestination(), map)) {
                action.getModel().updateProgress(deltaTime);
            } else {
                action.getModel().setProgress(0f);
                action.getModel().setMovingStatus(false);
                action.finished();
            }
        }
        action.getModel().setRotation(action.getModel().getDirection().getRotation());
        finishActionIfPossible(action);
//        isFinishedMovement(tankMoveModel);
    }

    public void executeMoveActions(float deltaTime, HashMap<TankMoveModel, Action> executingActionsQueue, Level level) {
//        tryToCatchNewCommand(executingActionsQueue, level);
//        executeMovement(deltaTime, level.getPlayerTank().getMoveModel(), level.getMap());
//
//        for (LevelNodeImpl levelNode : level.getMoveNodes()) {
//            executeMovement(deltaTime, levelNode.getMoveModel(), level.getMap());
//        }
//        executeMovement(deltaTime, executingActionsQueue., level.getMap());

        for (Action action : executingActionsQueue.values()) {
            executeMovement(deltaTime, action, level.getMap());
            finishActionIfPossible(action);
        }
//        for (TankMoveModel tankMoveModel : executingActionsQueue.keySet()) {
//            finishActionIfPossible(tankMoveModel, executingActionsQueue);
//        }

//        System.out.println("executingActionsQueue: ");
//        for (int i = 0; i < executingActionsQueue.size(); ++i) {
//            Action action = executingActionsQueue.get(i);
//            System.out.println("    type:" +
//                    action.getActionType() +
//                    " for whom: " +
//                    action.getModel().getCoordinates());
//        }
    }


}
