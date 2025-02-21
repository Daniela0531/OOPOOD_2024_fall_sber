package ru.mipt.bit.platformer.actions.impl_action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.ActionType;
import ru.mipt.bit.platformer.logic_objects.Model;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;

public class MoveAction implements Action {
    private final ActionType actionType = ActionType.MOVEMENT;
    private final Direction direction;
    private final GridPoint2 destinationCoordinates;
    private final MoveModel moveModel;
    private boolean isFinished = false;

    public MoveAction(MoveModel tankMoveModel, Direction direction) {
        this.direction = direction;
        this.moveModel = tankMoveModel;
        this.destinationCoordinates = new GridPoint2(
                tankMoveModel.getCoordinates().x + direction.getVector().x,
                tankMoveModel.getCoordinates().y + direction.getVector().y
        );
    }
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    @Override
    public ActionType getActionType() {
        return actionType;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public Model getModel() {
        return moveModel;
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
    @Override
    public boolean equals(Action action) {
        if (action instanceof SwitchHealthBar) {
            return action.getModel().equalsTo(moveModel);
        }
        return false;
    }
}
