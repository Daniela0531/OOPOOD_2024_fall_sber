package ru.mipt.bit.platformer.game_management.actions;

import ru.mipt.bit.platformer.game_objects.MoveModel;

public interface Action {
//    GridPoint2 getDestinationCoordinates();

    ActionType getActionType();

//    Direction getDirection();

    MoveModel getModel();
    boolean isFinished();

    void finished();
}
