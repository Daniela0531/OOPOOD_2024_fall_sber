package ru.mipt.bit.platformer.logic_execution;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.actions.impl_action.SwitchHealthBar;
import ru.mipt.bit.platformer.game_management.ExecutingActionsQueue;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.logic_objects.DamageDealerModel;
import ru.mipt.bit.platformer.logic_objects.LivableModel;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.logic_objects.ShootableModel;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.logic_objects.tree.TreeMoveModel;

import static com.badlogic.gdx.math.MathUtils.isEqual;


@Component
public class LogicExecutor {
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

    public void finishMoveActionIfPossible(MoveAction action) {
        if (isEqual(((MoveModel)action.getModel()).getProgress(), 1f)) {
            ((MoveModel)action.getModel()).finishMovement();
            ((MoveModel)action.getModel()).setProgress(0f);
            ((MoveModel)action.getModel()).setMovingStatus(false);
            action.finished();
        }
    }
    public void finishShootAction(ShootAction action) {
        action.getBullet().setProgress(0f);
        action.getBullet().setMovingStatus(false);
        ((ShootableModel) action.getModel()).finishShooting();
        action.finished();
    }

    public void execute(float deltaTime, Action action, Level level) {
        if (action instanceof MoveAction) {
            ((MoveModel) action.getModel()).setMovingStatus(true);
            executeTankMovement(deltaTime, (MoveAction) action, level);
            ((MoveModel)action.getModel()).setRotation(((MoveModel)action.getModel()).getDirection().getRotation());
            if (isEqual(((MoveModel)action.getModel()).getProgress(), 1f)) {
                finishMoveActionIfPossible((MoveAction) action);
            }
        }
        if (action instanceof ShootAction) {
            ((ShootAction) action).getBullet().setMovingStatus(true);
            ((ShootableModel) action.getModel()).updateFireProgress();
            executeBulletMovement(deltaTime, (ShootAction) action, level);
        }
        if (action instanceof SwitchHealthBar) {
            executeSwitchingHealthBar(deltaTime, (SwitchHealthBar) action, level);
        }
    }

    private void executeSwitchingHealthBar(float deltaTime, SwitchHealthBar action, Level level) {
        ((LivableModel)action.getModel()).switchHealthBar();
        action.finished();
//        System.out.println("up health bar");
    }

    public void executeTankMovement(float deltaTime, MoveAction action, Level level) {
        if (!movementIsPossible(((MoveModel)action.getModel()), level)) {
            ((MoveModel)action.getModel()).updateProgress(deltaTime);
            ((MoveModel)action.getModel()).setProgress(0f);
            ((MoveModel)action.getModel()).setMovingStatus(false);
            action.finished();
        }
    }
    public void executeBulletMovement(float deltaTime, ShootAction action, Level level) {
        GridPoint2 newCoordinates = action.getBullet().getCoordinates().cpy();
        if (!(newCoordinates.x < leftBound || newCoordinates.x > rightBound || newCoordinates.y < lowBound || newCoordinates.y > upBound)) {
            for(TreeMoveModel obstacle : level.getTrees().keySet()) {
                if (obstacle.getCoordinates().equals(newCoordinates)) {
                    finishShootAction(action);
                    return;
                }
            }
            for(TankMoveModel tank : level.getTanks().keySet()) {
                if (tank.getCoordinates().equals(newCoordinates)) {
                    tank.damage(((DamageDealerModel)action.getBullet()).getDamage());
                    finishShootAction(action);
                    return;
                }
            }
            if (!level.isPlayerKilled() && level.getPlayerTank().getCoordinates().equals(newCoordinates)) {
                level.getPlayerTank().damage(((DamageDealerModel)action.getBullet()).getDamage());
                finishShootAction(action);
                return;
            }
            action.getBullet().finishMovement();
            action.getBullet().updateProgress(deltaTime);
        } else {
            finishShootAction(action);
        }
    }

    public void executeActions(float deltaTime, ExecutingActionsQueue executingActionsQueue, Level level) {
        for (Action action : executingActionsQueue.getActions()) {
//            action.getModel().mainUpdateProgress(deltaTime);
            execute(deltaTime, action, level);
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
