package ru.mipt.bit.platformer.actions.impl_action;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.ActionType;
import ru.mipt.bit.platformer.logic_objects.LivableModel;
import ru.mipt.bit.platformer.logic_objects.Model;

public class SwitchHealthBar implements Action {
    private final LivableModel livableModel;
    private boolean isFinished = false;
    private ActionType actionType;
    public SwitchHealthBar(LivableModel livableModel) {
        actionType = ActionType.SWITCHING_HEALTH_BAR;
        this.livableModel = livableModel;
        this.isFinished = false;
    }
    @Override
    public ActionType getActionType() {
        return actionType;
    }
    @Override
    public Model getModel() {
        return livableModel;
    }
    @Override
    public boolean isFinished() {
        return isFinished;
    }
    @Override
    public void finished() {
        this.isFinished = true;
    }
    @Override
    public boolean equals(Action action) {
        if (action instanceof SwitchHealthBar) {
            return action.getModel().equalsTo(livableModel);
        }
        return false;
    }
}
