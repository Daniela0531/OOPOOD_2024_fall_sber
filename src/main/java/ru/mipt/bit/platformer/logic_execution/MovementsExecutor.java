package ru.mipt.bit.platformer.logic_execution;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.logic_objects.DamageDealerModel;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.logic_objects.tree.TreeMoveModel;

import java.util.HashMap;

import static com.badlogic.gdx.math.MathUtils.isEqual;


@Component
public class MovementsExecutor {
    private int upBound = 5;
    private int leftBound = 0;
    private int lowBound = 0;
    private int rightBound = 9;


    public boolean movementIsPossible(MoveModel tank, Level level) {
        GridPoint2 newCoordinates = tank.getDestination().cpy();
        if (newCoordinates.x < leftBound || newCoordinates.x > rightBound || newCoordinates.y < lowBound || newCoordinates.y > upBound) {
            return false;
        }
        for(TreeMoveModel obstacle : level.getTrees().keySet()) {
            if (obstacle.getCoordinates().equals(newCoordinates)) {
                return false;
            }
        }
        for(TankMoveModel otherTank : level.getTanks().keySet()) {
            if (otherTank.getCoordinates() == tank.getCoordinates()) {
                continue;
            }
            if (otherTank.getCoordinates().equals(newCoordinates) || otherTank.getDestination().equals(newCoordinates)) {
//                System.out.println(
//                        "tank try to move to newCoordinates: " + newCoordinates + "\n" +
//                        "    another tank coord: " + otherTank.getCoordinates() + "\n" +
//                        "                 dest: " + otherTank.getDestination());
                return false;
            }
        }
        if (level.getPlayerTank() != tank) {
            if (level.getPlayerTank().getCoordinates().equals(newCoordinates) || level.getPlayerTank().getDestination().equals(newCoordinates)) {
                return false;
            }
        }
        return true;
    }

    public void finishMoveActionIfPossible(Action action) {
        if (isEqual(action.getModel().getProgress(), 1f)) {
            action.getModel().finishMovement();
            action.getModel().setProgress(0f);
            action.getModel().setMovingStatus(false);
            action.finished();
        }
    }

    public void executeMovement(float deltaTime, Action action, Level level) {
            if (action instanceof MoveAction) {
                executeTankMovement(deltaTime, (MoveAction) action, level);
                action.getModel().setRotation(action.getModel().getDirection().getRotation());
                finishMoveActionIfPossible(action);
            }
            if (action instanceof ShootAction) {
                executeBulletMovement(deltaTime, (ShootAction) action, level);
            }
    }

    public void executeTankMovement(float deltaTime, MoveAction action, Level level) {
        if (movementIsPossible(action.getModel(), level)) {
            action.getModel().updateProgress(deltaTime);
        } else {
            action.getModel().setProgress(0f);
            action.getModel().setMovingStatus(false);
            action.finished();
        }
    }
    public void executeBulletMovement(float deltaTime, ShootAction action, Level level) {
        GridPoint2 newCoordinates = action.getBullet().getCoordinates().cpy();
        if (!(newCoordinates.x < leftBound || newCoordinates.x > rightBound || newCoordinates.y < lowBound || newCoordinates.y > upBound)) {
            for(TreeMoveModel obstacle : level.getTrees().keySet()) {
                if (obstacle.getCoordinates().equals(newCoordinates)) {
                    action.getBullet().setProgress(0f);
                    action.getBullet().setMovingStatus(false);
                    action.finished();
                    return;
                }
            }
            for(TankMoveModel tank : level.getTanks().keySet()) {
                if (tank.getCoordinates().equals(newCoordinates)) {
                    tank.damage(((DamageDealerModel)action.getBullet()).getDamage());
                    action.getBullet().setProgress(0f);
                    action.getBullet().setMovingStatus(false);
                    action.finished();
                    return;
                }
            }
            if (!level.isPlayerKilled() && level.getPlayerTank().getCoordinates().equals(newCoordinates)) {
                level.getPlayerTank().damage(((DamageDealerModel)action.getBullet()).getDamage());
                action.getBullet().setProgress(0f);
                action.getBullet().setMovingStatus(false);
                action.finished();
                return;
            }
            action.getBullet().finishMovement();
            action.getBullet().updateProgress(deltaTime);
        } else {
            action.getBullet().setProgress(0f);
            action.getBullet().setMovingStatus(false);
            action.finished();
        }
    }

    public void executeMoveActions(float deltaTime, HashMap<MoveModel, Action> executingActionsQueue, Level level) {
        for (Action action : executingActionsQueue.values()) {
            executeMovement(deltaTime, action, level);
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
