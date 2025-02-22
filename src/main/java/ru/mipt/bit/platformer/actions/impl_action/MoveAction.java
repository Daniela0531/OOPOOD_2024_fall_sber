package ru.mipt.bit.platformer.actions.impl_action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.logic_objects.Model;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;

public class MoveAction implements Action {
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

    public Direction getDirection() {
        return direction;
    }

    @Override
    public Model getModel() {
        return moveModel;
    }
    public void execute() {
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
