package ru.mipt.bit.platformer.game_management.actions.impl_action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game_management.actions.Action;
import ru.mipt.bit.platformer.game_management.actions.ActionType;
import ru.mipt.bit.platformer.game_objects.MoveModel;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;

public class MoveAction implements Action {
    private final ActionType actionType = ActionType.MOVEMENT;
    private final Direction direction;
    private final GridPoint2 destinationCoordinates;
    private final MoveModel tankMoveModel;
    private boolean isFinished = false;

    public MoveAction(MoveModel tankMoveModel, Direction direction) {
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

    public MoveModel getModel() {
        return tankMoveModel;
    }
    public void execute() {
//        if (collisionDetector.canMove(object, direction)) {
//            object.move(direction);
//        }
    }
    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void finished() {
        isFinished = true;
    }
}
