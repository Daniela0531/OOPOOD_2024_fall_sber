package ru.mipt.bit.platformer.game_management.actions.impl_action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game_management.actions.Action;
import ru.mipt.bit.platformer.game_management.actions.ActionType;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;

public class ShootAction implements Action {
    private final ActionType actionType = ActionType.SHOOTING;
    private final Direction direction;
    private final GridPoint2 destinationCoordinates;
    private final TankMoveModel tankMoveModel;
    private boolean isFinished = false;

    public ShootAction(TankMoveModel tankMoveModel, Direction direction) {
        this.direction = direction;
        this.tankMoveModel = tankMoveModel;
        this.destinationCoordinates = new GridPoint2(
                tankMoveModel.getCoordinates().x + direction.getVector().x,
                tankMoveModel.getCoordinates().y + direction.getVector().y
        );
    }
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
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
