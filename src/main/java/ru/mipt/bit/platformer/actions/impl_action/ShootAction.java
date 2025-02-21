package ru.mipt.bit.platformer.actions.impl_action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.ActionType;
import ru.mipt.bit.platformer.logic_objects.Model;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.logic_objects.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;

public class ShootAction implements Action {
    private final ActionType actionType = ActionType.SHOOTING;
    private final Direction direction;
    private final TankMoveModel tankMoveModel;
    private final BulletMoveModel bulletMoveModel;
    private boolean isFinished = false;

    public ShootAction(TankMoveModel tankMoveModel,
                       BulletMoveModel bulletMoveModel) {
        this.direction = tankMoveModel.getDirection();
        this.tankMoveModel = tankMoveModel;
        this.bulletMoveModel = bulletMoveModel;
    }
    public GridPoint2 getDestinationCoordinates() {
        return new GridPoint2(
                bulletMoveModel.getCoordinates().x + bulletMoveModel.getDirection().getVector().x,
                bulletMoveModel.getCoordinates().y + bulletMoveModel.getDirection().getVector().y);
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Model getModel() {
        return tankMoveModel;
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

    public MoveModel getBullet() {
        return bulletMoveModel;
    }
    @Override
    public boolean equals(Action action) {
        if (action instanceof SwitchHealthBar) {
            return action.getModel().equalsTo(bulletMoveModel);
        }
        return false;
    }
}
