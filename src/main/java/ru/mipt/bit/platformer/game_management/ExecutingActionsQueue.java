package ru.mipt.bit.platformer.game_management;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.actions.impl_action.SwitchHealthBar;
import ru.mipt.bit.platformer.game_input_management.CommandQueue;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.logic_objects.ShootableModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExecutingActionsQueue {
    private Set<Action> executingActions;
    public ExecutingActionsQueue() {
        this.executingActions = new HashSet<>();
    }
    public void catchingNewActions(CommandQueue commandQueue) {
        if (commandQueue.isEmpty()) {
            return;
        }
        for (Action potentialNewAction : commandQueue.getActions()) {
            if (potentialNewAction instanceof MoveAction) {
                if (((MoveModel) potentialNewAction.getModel()).isMoving()) {
                    continue;
                }
                ((MoveModel) potentialNewAction.getModel()).setDirection((((MoveAction) potentialNewAction).getDirection()));
                ((MoveModel) potentialNewAction.getModel()).setMovingStatus(true);
                executingActions.add(potentialNewAction);
            }
            if (potentialNewAction instanceof ShootAction) {
                if (!((ShootableModel) potentialNewAction.getModel()).mayShoot()) {
                    continue;
                }
                ((ShootAction) potentialNewAction).getBullet().setMovingStatus(true);
                executingActions.add(potentialNewAction);
            }
            if (potentialNewAction instanceof SwitchHealthBar) {
//                System.out.println("SwitchHealthBar possible to switch: " + ((LivableModel) potentialNewAction.getModel()).maySwitchHealthBar());
//                if (!((LivableModel) potentialNewAction.getModel()).maySwitchHealthBar()) {
//                    continue;
//                }
//                ((LivableModel) potentialNewAction.getModel()).switchHealthBar();
//                System.out.println("SwitchHealthBar action is cached");
//                System.out.println("    healthBarCur: " + ((TankMoveModel) potentialNewAction.getModel()).getHealthBarCur());
                executingActions.add(potentialNewAction);
            }
        }
        commandQueue.clear();
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
