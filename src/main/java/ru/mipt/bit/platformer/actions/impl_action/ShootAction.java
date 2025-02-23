package ru.mipt.bit.platformer.actions.impl_action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.logic_objects.DamageDealerModel;
import ru.mipt.bit.platformer.logic_objects.Model;
import ru.mipt.bit.platformer.logic_objects.ShootableModel;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.logic_objects.tree.TreeMoveModel;

public class ShootAction implements Action {
    private final Direction direction;
    private final ShootableModel shootableModel;
    private final DamageDealerModel damageDealerModel;
    private boolean isFinished = false;

    public ShootAction(ShootableModel shootableModel,
                       DamageDealerModel damageDealerModel, Direction direction) {
        this.direction = direction;
        this.shootableModel = shootableModel;
        this.damageDealerModel = damageDealerModel;
    }
    public GridPoint2 getDestinationCoordinates() {
        return new GridPoint2(
                damageDealerModel.getCoordinates().x + direction.getVector().x,
                damageDealerModel.getCoordinates().y + direction.getVector().y);
    }
    @Override
    public Model getModel() {
        return (Model) shootableModel;
    }
    public void execute() {
    }
    @Override
    public boolean isFinished() {
        return isFinished;
    }
    @Override
    public void finished() {
        this.isFinished = true;
    }

    @Override
    public void execute(float deltaTime, Level level) {
        damageDealerModel.setMovingStatus(true);
        shootableModel.updateFireProgress();
        executeBulletMovement(deltaTime, level);
    }
    private void executeBulletMovement(float deltaTime, Level level) {
        GridPoint2 newCoordinates = damageDealerModel.getCoordinates().cpy();
        if (!(newCoordinates.x < level.getLeftBound() ||
                newCoordinates.x > level.getRightBound() ||
                newCoordinates.y < level.getLowBound() ||
                newCoordinates.y > level.getUpBound())) {
            for(TreeMoveModel obstacle : level.getTrees().keySet()) {
                if (obstacle.getCoordinates().equals(newCoordinates)) {
                    finishShootAction();
                    return;
                }
            }
            for(TankMoveModel tank : level.getTanks().keySet()) {
                if (tank.getCoordinates().equals(newCoordinates)) {
                    tank.damage(damageDealerModel.getDamage());
                    finishShootAction();
                    return;
                }
            }
            if (!level.isPlayerKilled() && level.getPlayerTank().getCoordinates().equals(newCoordinates)) {
                level.getPlayerTank().damage(damageDealerModel.getDamage());
                finishShootAction();
                return;
            }
            damageDealerModel.finishMovement();
            damageDealerModel.updateProgress(deltaTime);
        } else {
            finishShootAction();
        }
    }
    private void finishShootAction() {
        damageDealerModel.setProgress(0f);
        damageDealerModel.setMovingStatus(false);
        shootableModel.finishShooting();
        finished();
    }

    public DamageDealerModel getBullet() {
        return damageDealerModel;
    }

}
