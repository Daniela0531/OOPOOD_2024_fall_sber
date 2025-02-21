package ru.mipt.bit.platformer.actions;

import ru.mipt.bit.platformer.logic_objects.MoveModel;

public interface Action {
//    GridPoint2 getDestinationCoordinates();

    ActionType getActionType();

//    Direction getDirection();

    MoveModel getModel();
    boolean isFinished();

    void finished();
}
