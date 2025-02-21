package ru.mipt.bit.platformer.actions;

import ru.mipt.bit.platformer.logic_objects.Model;

public interface Action {
    ActionType getActionType();
    Model getModel();
    boolean isFinished();
    void finished();
    boolean equals(Action action);
}
