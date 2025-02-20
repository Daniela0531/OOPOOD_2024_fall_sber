package ru.mipt.bit.platformer.game_management.actions.impl_action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game_management.actions.Action;
import ru.mipt.bit.platformer.game_management.actions.ActionType;
import ru.mipt.bit.platformer.game_objects.movable.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;

public class ShootAction implements Action {
    private final ActionType actionType = ActionType.SHOOTING;
    private final Direction direction;
    private final GridPoint2 destinationCoordinates;
    private final TankMoveModel tankMoveModel;
    private final BulletMoveModel bulletMoveModel;
    private boolean isFinished = false;

    public ShootAction(TankMoveModel tankMoveModel,
                       BulletMoveModel bulletMoveModel) {
        this.direction = tankMoveModel.getDirection();
        this.tankMoveModel = tankMoveModel;
        this.bulletMoveModel = bulletMoveModel;
        this.destinationCoordinates = new GridPoint2(
                tankMoveModel.getCoordinates().x + direction.getVector().x,
                tankMoveModel.getCoordinates().y + direction.getVector().y
        );
    }
    public GridPoint2 getDestinationCoordinates() {
        return new GridPoint2(
                bulletMoveModel.getCoordinates().x + direction.getVector().x,
                bulletMoveModel.getCoordinates().y + direction.getVector().y);
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Direction getDirection() {
        return direction;
    }

    public TankMoveModel getModel() {
        return tankMoveModel;
    }
    public BulletMoveModel getBullet() {
        return bulletMoveModel;
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
}
