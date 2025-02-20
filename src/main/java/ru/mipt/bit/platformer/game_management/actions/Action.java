package ru.mipt.bit.platformer.game_management.actions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game_objects.MoveModel;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;

public interface Action {
    GridPoint2 getDestinationCoordinates();

    ActionType getActionType();

    Direction getDirection();

    MoveModel getModel();
    boolean isFinished();

    void finished();
}
