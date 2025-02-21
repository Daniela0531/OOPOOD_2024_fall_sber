package ru.mipt.bit.platformer.game_management;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.graphics_properties.GraphicProperties;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.graphics.MainGraphicRender;
import ru.mipt.bit.platformer.logic_execution.MovementsExecutor;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.level.Level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Component
public class MainCommandExecutor {
    private MovementsExecutor movementCommandExecuter;
    private MainGraphicRender graphicRender;
    private HashMap<MoveModel, Action> executingActions;
    public MainCommandExecutor(GraphicProperties graphicProperties, Level level) {
        this.graphicRender = new MainGraphicRender(graphicProperties, level);
        this.movementCommandExecuter = new MovementsExecutor();
        this.executingActions = new HashMap<>();
    }

    private void tryToCatchNewCommand(CommandQueueHandler commandQueueHandler, Level level) {
        if (commandQueueHandler.isEmpty()) {
            return;
        }
        for (Action action : commandQueueHandler.getActions()) {
//            if (executingActions.containsKey(action.getModel())) {
//                if (action instanceof ShootAction) {
//                    executingActions.put(action.getModel(), action);
//                    System.out.println("CATCH NEW ONE ShootAction for moving tank");
//                }
//            } else {
            if (!executingActions.containsKey(action.getModel())) {
                if (action instanceof MoveAction) {
                    action.getModel().setDirection((((MoveAction) action).getDirection()));
                    action.getModel().setMovingStatus(true);
                    executingActions.put(action.getModel(), action);
//                    System.out.println("CATCH NEW ONE MoveAction");
                }
                if (action instanceof ShootAction) {
//                    ((ShootAction) action).getBullet().setDirection(((ShootAction) action).getDirection());
                    ((ShootAction) action).getBullet().setMovingStatus(true);
                    executingActions.put(action.getModel(), action);
//                    System.out.println("CATCH NEW ONE ShootAction (for not moving tank)");
                }

            }
        }
        commandQueueHandler.clear();
    }

    private void removeFinishedActions() {
        Collection<MoveModel> allActions = new ArrayList<>();
        allActions.addAll(executingActions.keySet());
        for(MoveModel action : allActions) {
            if (executingActions.get(action).isFinished()) {
                executingActions.remove(action);
            }
        }
    }

    public void executeAllCommands(
            float deltaTime,
            CommandQueueHandler commandQueueHandler,
            Level level
    ) {
        graphicRender.clear();
        tryToCatchNewCommand(commandQueueHandler, level);
//        printQueue();
        movementCommandExecuter.executeMoveActions(deltaTime, executingActions, level);
        graphicRender.render(deltaTime, level);
        level.removeKilledTanks();
        level.removeFinishedBullets();
        removeFinishedActions();
//        printQueue();
    }

    public void dispose(Level level) {
        graphicRender.dispose(level);
    }

    private void printQueue() {
        System.out.println("my queue:");
        for(Action action : executingActions.values()) {
            System.out.println("    " + action.getActionType() +
                    ": coord " + action.getModel().getCoordinates() +
                    "  direction" + action.getModel().getDirection().getVector() +
                    "  progres: " + action.getModel().getProgress());
        }
    }
}
