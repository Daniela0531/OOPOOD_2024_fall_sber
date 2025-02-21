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

//    public boolean shootingIsPossible(GridPoint2 destCoordinates, Map map) {
//        GridPoint2 newCoordinates = destCoordinates.cpy();
//        if (newCoordinates.x < leftBound || newCoordinates.x > rightBound || newCoordinates.y < lowBound || newCoordinates.y > upBound) {
//            return false;
//        }
//        for(MapNode obstacleCoordinates : map.getNodes()) {
//            if (obstacleCoordinates.getCoordinates().equals(newCoordinates)) {
//                return false;
//            }
//        }
//        return true;
//    }

    public void finishMoveActionIfPossible(Action action) {
        if (isEqual(action.getModel().getProgress(), 1f)) {
            action.getModel().finishMovement();
            action.getModel().setProgress(0f);
            action.getModel().setMovingStatus(false);
            action.finished();
        }
    }

    public void executeMovement(float deltaTime, Action action, Level level) {

//        System.out.println("executeMovement");
//        if (action.getModel().isMoving()) {
//            System.out.println("    isMoving");
            if (action.getActionType() == ActionType.MOVEMENT) {
                executeTankMovement(deltaTime, (MoveAction) action, level.getMap());
                action.getModel().setRotation(action.getModel().getDirection().getRotation());
                finishMoveActionIfPossible(action);
            }
            if (action.getActionType() == ActionType.SHOOTING) {
                System.out.println("executeMovement == ActionType.SHOOTING");
                executeBulletMovement(deltaTime, (ShootAction) action, level);
            }
//        }
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
//        printBullet(action);
        GridPoint2 newCoordinates = action.getBullet().getDestination().cpy();
//        System.out.println("start executeBulletMovement to dest: " + newCoordinates);
//        printBullet(action);
        if (!(newCoordinates.x < leftBound || newCoordinates.x > rightBound || newCoordinates.y < lowBound || newCoordinates.y > upBound)) {
//            System.out.println("after if");
//            printBullet(action);
            for(MapNode obstacle : level.getMap().getNodes()) {
//                System.out.println("in for");
//                printBullet(action);
                if (obstacle.getCoordinates().equals(newCoordinates)) {
                    if (obstacle.getNodeType() == NodeType.TANK) {
//                        action.getModel().damage(action.getModel().getDamage());
//                        action.getBullet().updateProgress(deltaTime);
                        action.getBullet().setProgress(0f);
                        action.getBullet().setMovingStatus(false);
                        action.finished();
                        System.out.println("damage tank");
                        printBullet(action);
                        return;
//                        level.removeBullet((BulletMoveModel) action.getModel());
                    }
                    if (obstacle.getNodeType() == NodeType.TREE) {
                        action.getBullet().setProgress(0f);
                        action.getBullet().setMovingStatus(false);
                        action.finished();
//                        System.out.println("finished:");
//                        printBullet(action);
                        System.out.println("stop in tree");
                        printBullet(action);
                        return;
                    }
                }
            }
//            action.getBullet().updateProgress(deltaTime);
            action.getBullet().finishMovement();
            action.getBullet().updateProgress(deltaTime);
            System.out.println("update progres");
            printBullet(action);
        } else {
            action.getModel().setMovingStatus(false);
            action.finished();
            System.out.println("dont upgrade progress");
            printBullet(action);
        }
    }

    public void executeMoveActions(float deltaTime, HashMap<MoveModel, Action> executingActionsQueue, Level level) {
        System.out.println("executeMoveActions size: " + executingActionsQueue.size());
        for (Action action : executingActionsQueue.values()) {
            executeMovement(deltaTime, action, level);
//            finishMoveActionIfPossible(action);
        }
    }

    private void printBullet(ShootAction action) {
        System.out.println("bullet\n" +
                "    status is move: " + action.getBullet().isMoving() + "\n" +
                "    shoot from: " + action.getBullet().getCoordinates() + "\n" +
                "    in direction: " + action.getBullet().getDirection().getVector() + "\n" +
                "    progres: " + action.getBullet().getProgress() + "\n" +
                "    destination: " + action.getBullet().getDestination());
    }

}
