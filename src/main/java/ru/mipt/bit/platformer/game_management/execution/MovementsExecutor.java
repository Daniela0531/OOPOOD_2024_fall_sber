package ru.mipt.bit.platformer.game_management.execution;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.Map;
import ru.mipt.bit.platformer.game_management.actions.Action;
import ru.mipt.bit.platformer.game_management.actions.ActionType;
import ru.mipt.bit.platformer.game_management.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.game_management.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.game_objects.MoveModel;
import ru.mipt.bit.platformer.game_objects.NodeType;
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

    public boolean shootingIsPossible(GridPoint2 destCoordinates, Map map) {
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

//    public void tryToCatchNewCommand(CommandQueueHandler commandQueueHandler) {
//        if (commandQueueHandler.isEmpty()) {
//            return;
//        }
//        for (Action action : commandQueueHandler.getActions()) {
//            if (!action.getModel().isMoving()) {
//                action.getModel().setDirection(action.getDirection());
//                action.getModel().setMovingStatus(true);
//            }
//        }
//        commandQueueHandler.clear();
//    }

    public void finishMoveActionIfPossible(Action action) {
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

    public void executeMovement(float deltaTime, Action action, Level level) {
        if (action.getModel().isMoving()) {
            if (action.getActionType() == ActionType.MOVEMENT) {
                executeTankMovement(deltaTime, (MoveAction) action, level.getMap());
                action.getModel().setRotation(action.getModel().getDirection().getRotation());
                finishMoveActionIfPossible(action);
            }
            if (action.getActionType() == ActionType.SHOOTING) {
                executeBulletMovement(deltaTime, (ShootAction) action, level);
            }
        }
//        isFinishedMovement(tankMoveModel);
    }

    public void executeTankMovement(float deltaTime, MoveAction action, Map map) {
        if (movementIsPossible(action.getModel().getDestination(), map)) {
            action.getModel().updateProgress(deltaTime);
        } else {
            action.getModel().setProgress(0f);
            action.getModel().setMovingStatus(false);
            action.finished();
        }
    }
    public void executeBulletMovement(float deltaTime, ShootAction action, Level level) {
        printBullet(action);
        GridPoint2 newCoordinates = action.getDestinationCoordinates().cpy();
        if (!(newCoordinates.x < leftBound || newCoordinates.x > rightBound || newCoordinates.y < lowBound || newCoordinates.y > upBound)) {
            for(MapNode obstacle : level.getMap().getNodes()) {
                if (obstacle.getCoordinates().equals(newCoordinates)) {
                    if (obstacle.getNodeType() == NodeType.TANK) {
                        action.getModel().damage(action.getBullet().getDamage());
                        action.getBullet().setMovingStatus(false);
                        action.finished();
                        level.removeBullet(action.getBullet());
                    } else {
                        action.getModel().setProgress(0f);
                        action.getBullet().setMovingStatus(false);
                        action.finished();
                        return;
                    }
                }
            }
            action.getBullet().finishMovement();
            action.getBullet().updateProgress(deltaTime);
        } else {
            action.getBullet().setMovingStatus(false);
            action.finished();
        }
//        System.out.println("Shoot: " + action.getBullet());
    }

    public void executeMoveActions(float deltaTime, HashMap<MoveModel, Action> executingActionsQueue, Level level) {
        for (Action action : executingActionsQueue.values()) {
            executeMovement(deltaTime, action, level);
            finishMoveActionIfPossible(action);
        }
    }

    private void printBullet(ShootAction action) {
        System.out.println("bullet\n" +
                "    shoot from: " + action.getModel().getCoordinates() + "\n" +
                "    in direction: " + action.getBullet().getDirection() + "\n" +
                "    status is move:" + action.getBullet().isMoving());
    }

}
