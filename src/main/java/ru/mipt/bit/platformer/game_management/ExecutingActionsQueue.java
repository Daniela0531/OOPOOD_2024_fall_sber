package ru.mipt.bit.platformer.game_management;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.actions.impl_action.SwitchHealthBar;
import ru.mipt.bit.platformer.game_management.input_management.CommandQueue;
import ru.mipt.bit.platformer.logic_objects.MoveModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExecutingActionsQueue {
    private Set<Action> executingActions;
    public ExecutingActionsQueue() {
        this.executingActions = new HashSet<>();
    }
    public void catchingNewActions(CommandQueue commandQueueHandler) {
        if (commandQueueHandler.isEmpty()) {
            return;
        }
        for (Action potentialNewAction : commandQueueHandler.getActions()) {
            boolean notExecute = true;
            for (Action executingAction : executingActions) {
                if (executingAction.equals(potentialNewAction)) {
                    notExecute = false;
                    break;
                }
            }
            if (notExecute) {
                if (potentialNewAction instanceof MoveAction) {
                    ((MoveModel) potentialNewAction.getModel()).setDirection((((MoveAction) potentialNewAction).getDirection()));
                    ((MoveModel) potentialNewAction.getModel()).setMovingStatus(true);
                    executingActions.add(potentialNewAction);
                }
                if (potentialNewAction instanceof ShootAction) {
                    ((ShootAction) potentialNewAction).getBullet().setMovingStatus(true);
                    executingActions.add(potentialNewAction);
                }
                if (potentialNewAction instanceof SwitchHealthBar) {
                    System.out.println("CATCH SWITCHING BAR");
                    executingActions.add(potentialNewAction);
                }
            }
        }
        commandQueueHandler.clear();
    }

    public void removeFinishedActions() {
        Collection<Action> allActions = new ArrayList<>(executingActions);
        for(Action action : allActions) {
            if (action.isFinished()) {
                executingActions.remove(action);
            }
        }
    }

    public Set<Action> getActions() {
        return executingActions;
    }
}
